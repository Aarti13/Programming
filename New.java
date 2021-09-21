 private static void process() throws BTSLBaseException
    {
        final String METHOD_NAME = "process";
        if (_logger.isDebugEnabled())
                        _logger.info("process ","Entered ");
        String processId =null;
        ProcessBL processBL=null;
        String finalDirectoryPath=null;
        Connection con=null;
        int beforeInterval=0;
        ProcessStatusVO processStatusVO=null;
        Date currentDate=null;
        Date processedUpto=null;
        int updateCount=0;                              //check process details are updated or not
        try
        {
            processId=ProcessI.BATCH_USER_CHANGE_STATUS;
            con= OracleUtil.getSingleConnection();
            processBL = new ProcessBL();
            processStatusVO=processBL.checkProcessUnderProcess(con,processId);
            beforeInterval=(int)processStatusVO.getBeforeInterval()/(60*24);
            if(processStatusVO.isStatusOkBool())
            {
                        //method call to find maximum date till which process has been executed
                   processedUpto=processStatusVO.getExecutedUpto();
                           if (processedUpto!=null)
                               {
                                currentDate= BTSLUtil.getDateFromDateString(BTSLUtil.getDateStringFromDate(new Date()));
                        /*        processedUpto= BTSLUtil.getDateFromDateString(BTSLUtil.getDateStringFromDate(processedUpto));
                                int diffDate= BTSLUtil.getDifferenceInUtilDates(processedUpto,currentDate);
                                currentDate=new Date();
                                if(diffDate<=1)
                                {
                                        _logger.error("process"," Process already executed.....");
                                throw new BTSLBaseException("BatchUserChangeStatus","process",PretupsErrorCodesI.BATCH_USER_CHANGE_STATUS_ALREADY_EXECUTED);
                                }

                                processedUpto=BTSLUtil.addDaysInUtilDate(currentDate,-(beforeInterval+1));*/
                                processedUpto = currentDate;
                                //call process for uploading transfer details
                                //boolean isDataProcessed=balanceAlert(con);

                                String dir = Constants.getProperty("UploadBatchUserChnageStatusFilePath");
 String fileSize = Constants.getProperty("MAX_XLS_FILE_SIZE");
                                String contentType = BTSLUtil.getFileContentType(PretupsI.FILE_CONTENT_TYPE_PLAIN_TEXT);
                                File directory = new File(dir);
                                File[] directoryListing = directory.listFiles();
                                boolean isFileProcessed=false;
                                 if (directoryListing != null && directoryListing.length > 0) {
                                         finalDirectoryPath=createDirectory();
                                        for (File child : directoryListing) {
                                isFileProcessed = processFile(child,dir,contentType,"batchuserchangestatus",Long.parseLong(fileSize),finalDirectoryPath);
                              }

                            } else {
                                System.out.println("No Files found in directory structure");
                            }

                                if(isFileProcessed)
                                {
                                  processStatusVO.setExecutedUpto(processedUpto);
                                  EventHandler.handle(EventIDI.SYSTEM_INFO,EventComponentI.SYSTEM,EventStatusI.RAISED,EventLevelI.MAJOR,"BatchUserChangeStatus[process]","","",""," Batch User Creation has been executed successfully.");
                                         if(_logger.isDebugEnabled())
                                          _logger.debug("process", "message sent successfully");
                                }
                                }
                           else
                               throw new BTSLBaseException("BatchUserChangeStatus","process",PretupsErrorCodesI.BATCH_USER_CHANGE_STATUS_EXECUTED_UPTO_DATE_NOT_FOUND);
            }
            else
                     throw new BTSLBaseException("BatchUserChangeStatus","process",PretupsErrorCodesI.PROCESS_ALREADY_RUNNING);
        }
        catch(BTSLBaseException be)
        {
                _logger.error("process", "BTSLBaseException : " + be.getMessage());
                _logger.errorTrace(METHOD_NAME,be);
                throw be;
        }
        catch(Exception e)
        {
            _logger.error("process", "Exception : " + e.getMessage());
            _logger.errorTrace(METHOD_NAME,e);
 finally
        {
            try
                {
                if (processStatusVO.isStatusOkBool())
                    {
                                processStatusVO.setStartDate(currentDate);
                                processStatusVO.setExecutedOn(currentDate);
                                processStatusVO.setProcessStatus(ProcessI.STATUS_COMPLETE);
                                        updateCount=(new ProcessStatusDAO()).updateProcessDetail(con,processStatusVO);
                                        if(updateCount>0)
                                    {
                                            con.commit();
                                    }
                        }
                        }
                        catch(Exception ex)
                        {
                                        if(_logger.isDebugEnabled())_logger.debug("process", "Exception in closing connection ");
                                        _logger.errorTrace(METHOD_NAME,ex);
                        }
            if(con!=null)try{con.close();}catch(SQLException e1){
                _logger.errorTrace(METHOD_NAME,e1);
            }
            if(_logger.isDebugEnabled())_logger.debug("process", "Exiting..... ");
        }
    }


    public static boolean processFile(File file,String p_dirPath,String p_contentType,String forward,long p_fileSize,String finalDirectroyPath) throws BTSLBaseException
    {
        BufferedReader br = null;
        String formattedDate=null;
        String methodName="processFile";
        ArrayList statusList=new ArrayList();
        String statusString=null;
        Connection con=null;
        try
        {
                String sCurrentLine;
                Date currDate=new Date();
 try
                {
                 formattedDate=BTSLUtil.getDateTimeStringFromDate(currDate, "dd-MM-yyyy hh:MM:ss");
                }
                catch(ParseException pe)
                {
                pe.printStackTrace();
                }
                ArrayList recordList=new ArrayList();
                        ChannelUserVO channelUserVO=null;
                        ChannelUserDAO chDAO=new ChannelUserDAO();
//                      _chUserid=_batchUserCreationProperties.getProperty("CHUSRID");
//                      _chPassword=_batchUserCreationProperties.getProperty("CHUSRPASSWD");
                        _chEmpCode=_batchOperationProperties.getProperty("CHEMPCODE");
                        _defCatCode=_batchOperationProperties.getProperty("DEFCATCODE");
                        _defUsrPrfx=_batchOperationProperties.getProperty("DEFUSRPRFX");
                        _netwrkCode=_batchOperationProperties.getProperty("NTWRKCODE");
                        _url=_batchOperationProperties.getProperty("URL");
                        _defaultPassword=_batchOperationProperties.getProperty("DEFPASSWD");
                        System.out.println("***"+file);
                        br = new BufferedReader(new FileReader(file));
                        con=OracleUtil.getSingleConnection();
                        String msisdn=null;
                        String newStatus=null;
                        while ((sCurrentLine = br.readLine()) != null) {
                                System.out.println(sCurrentLine);
                                channelUserVO=new ChannelUserVO();
                                msisdn=sCurrentLine.split("[|]")[0];
                                newStatus=sCurrentLine.split("[|]")[1];
                                channelUserVO=chDAO.loadChannelUserDetails(con,msisdn);
                                if(channelUserVO!=null){
                                        channelUserVO.setStatus(newStatus);
                                        recordList.add(channelUserVO);
                                }
                                else
                                        statusList.add(msisdn+"|Invalid User|25034|");
                        }


                        String urlToSend=null;
                        String httpURLPrefix="http://";
                        URL url=null;
                        PrintWriter out=null;
 URL url=null;
                        PrintWriter out=null;
                        BufferedReader in=null;
                        String responseStr = null;
                        String requestMessage = null;

                        for(int recordIndex=0;recordIndex<recordList.size();recordIndex++)
                        {
                                channelUserVO=(ChannelUserVO)recordList.get(recordIndex);
                                requestMessage="<?xml version=\"1.0\"?><!DOCTYPE COMMAND PUBLIC \"-//Ocam//DTD XMLCommand1.0//EN\"\"xml/command.dtd\"><COMMAND><TYPE>EXCHNGSTATUSREQ</TYPE><DATE>"+formattedDate+"</DATE><EXTNWCODE>"+_netwrkCode+"</EXTNWCODE><MSISDN>"+channelUserVO.getMsisdn()+"</MSISDN><PIN>"+BTSLUtil.decryptText(channelUserVO.getUserPhoneVO().getSmsPin())+"</PIN><LOGINID></LOGINID><PASSWORD></PASSWORD><EXTCODE></EXTCODE><EXTREFNUM></EXTREFNUM><STATE>"+channelUserVO.getStatus()+"</STATE><LANGUAGE1></LANGUAGE1></COMMAND>";
                                urlToSend=httpURLPrefix+_url+_batchOperationProperties.getProperty("C2S_RECEIVER");;
                                urlToSend =urlToSend +"?REQUEST_GATEWAY_CODE="+_batchOperationProperties.getProperty("REQUEST_GATEWAY_CODE")+"&REQUEST_GATEWAY_TYPE="+_batchOperationProperties.getProperty("REQUEST_GATEWAY_TYPE");
                                urlToSend =urlToSend +"&SERVICE_PORT="+_batchOperationProperties.getProperty("SERVICE_PORT")+"&LOGIN="+_batchOperationProperties.getProperty("LOGIN") ;
                                urlToSend =urlToSend +"&PASSWORD="+_batchOperationProperties.getProperty("PASSWORD") +"&SOURCE_TYPE="+_batchOperationProperties.getProperty("SOURCE_TYPE") ;
                                url=new URL(urlToSend);
                                _urlConnection=(HttpURLConnection)url.openConnection();
                                _urlConnection.setConnectTimeout(10000);
                                _urlConnection.setReadTimeout(10000);
                                _urlConnection.setDoOutput(true);
                                _urlConnection.setDoInput(true);
                                _urlConnection.addRequestProperty("Content-Type", "text/xml");
                                _urlConnection.setRequestMethod("POST");
                                StringBuffer buffer = new StringBuffer();
                                String respStr = "";
                                try
                                {
                                        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(_urlConnection.getOutputStream())),true);
                                        if(_logger.isDebugEnabled())_logger.debug(methodName,"Request sent   ="+requestMessage);
                                        out.println(requestMessage);
                                        out.flush();
                                        in = new BufferedReader(new InputStreamReader(_urlConnection.getInputStream()));
                                        while ((respStr = in.readLine()) != null)
                                        {
                                                buffer.append(respStr);
                                        }
                                }
                                                                       

                    EventHandler.handle(EventIDI.SYSTEM_INFO,EventComponentI.SYSTEM,EventStatusI.RAISED,EventLevelI.MAJOR,"BatchUserChangeStatus[process]","","",""," BatchUserChangeStatus process could not be executed successfully.");
                    throw new BTSLBaseException("BatchUserChangeStatus","process",PretupsErrorCodesI.ERROR_IN_DAILY_ALERT);
					 catch(Exception e)
                                {
                                        _logger.errorTrace(methodName,e);
                                        _logger.error(methodName,"Exception in reading or writing  e:"+e.getMessage());
                                }//end of catch-Exception
                                finally
                                {
                                        try{if(out!=null)out.close();}catch(Exception e){_logger.errorTrace(methodName,e);}
                                        try{if(in!=null)in.close();}catch(Exception e){_logger.errorTrace(methodName,e);}
                                }//end of finally
                                responseStr = buffer.toString();
                                System.out.println("Response Received :: "+responseStr);
                                statusString=parseResponse(responseStr,channelUserVO.getMsisdn());
                                System.out.println("Parsed Response :: "+statusString);
                                statusList.add(statusString);
                                if(_logger.isDebugEnabled())_logger.debug(methodName,"Response Received   ="+responseStr);
                        }



                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        try{ if(con!=null)      con.close(); }catch(Exception e){}
                        writeToFile(finalDirectroyPath,statusList,file.getName());
                        moveFilesToFinalDirectory(p_dirPath, finalDirectroyPath+"/",file.getName());
                        try {
                                if (br != null)br.close();
                        } catch (IOException ex) {
                                ex.printStackTrace();
                        }
                }
        return true;
    }


 public static String parseResponse(String response,String msisdn)
    {
        if (_logger.isDebugEnabled())
                _logger.debug("parseResponse","Entered ::"+response+"           MSISDN :: "+msisdn);
        String message=null;
        String txnStatus=null;
        String finalResponse="";
        ArrayList sList=new ArrayList();
        txnStatus=response.substring(response.indexOf("<TXNSTATUS>")+"<TXNSTATUS>".length(),response.indexOf("</TXNSTATUS>"));
        message=response.substring(response.indexOf("<MESSAGE>")+"<MESSAGE>".length(),response.indexOf("</MESSAGE>"));
        finalResponse=msisdn+"|"+message+"|"+txnStatus;
        System.out.println("Final response returned :: "+finalResponse);
        return finalResponse;

    }
    public static void writeToFile(String finalDirectoryPath,ArrayList statusList,String fileName)
    {
        if (_logger.isDebugEnabled())
                        _logger.debug("writeToFile","Entered with FinalDirectoryPath ::"+finalDirectoryPath+"statusList size"+statusList.size()+" File Name :: "+fileName);
        String sucFileName=null;
        String failFileName=null;
        String message=null;
        PrintWriter sucWriter=null;
        PrintWriter failWriter=null;
        sucFileName=finalDirectoryPath+"/"+fileName.split("[.]")[0]+"_Success.etuchgst";
        failFileName=finalDirectoryPath+"/"+fileName.split("[.]")[0]+".etuchgstres";
        try
        {
                String status=null;
                 sucWriter = new PrintWriter(sucFileName, "UTF-8");
                 failWriter= new PrintWriter(failFileName, "UTF-8");
                for(int statusCount=0;statusCount<statusList.size();statusCount++)
                {
                        status=((String)statusList.get(statusCount)).split("[|]")[2];
                        message=((String)statusList.get(statusCount)).split("[|]")[0]+"|"+((String)statusList.get(statusCount)).split("[|]")[1]+"|";
                        if("200".equalsIgnoreCase(status))
                         sucWriter.println(message);
                        else
                        failWriter.println(message);
                }
 This method will copy all the created files to another location.
         * the process will generate files in a particular directroy. if the process thats has to read files strarts before copletion of the file generation,
         * errors will occur. so a different directory is created and files are moved to that final directory.
         * @param p_oldDirectoryPath String
         * @param p_finalDirectoryPath String
         * @param p_processId String
         * @param p_beingProcessedDate Date
         * @throws BTSLBaseException
         * @return String
         */
        private static void moveFilesToFinalDirectory(String p_oldDirectoryPath,String p_finalDirectoryPath,String fileName) throws BTSLBaseException
        {
                final String METHOD_NAME = "moveFilesToFinalDirectory";
                if (_logger.isDebugEnabled())
                        _logger.debug("moveFilesToFinalDirectory"," Entered: p_oldDirectoryPath="+p_oldDirectoryPath+" p_finalDirectoryPath="+p_finalDirectoryPath+"fileName ="+fileName);

                String oldFileName=null;
                String newFileName=null;
                File oldFile=null;
                File newFile=null;
                File parentDir = new File(p_finalDirectoryPath);
                if(!parentDir.exists())
                        parentDir.mkdirs();
                //child directory name includes a file name and being processed date, month and year


                File oldDir = new File(p_oldDirectoryPath);
                File newDir = new File(p_finalDirectoryPath);
                if(!newDir.exists())
                        newDir.mkdirs();

                if(_logger.isDebugEnabled())
                    _logger.debug("moveFilesToFinalDirectory", " newDirName="+p_finalDirectoryPath);
                try
                {
                                oldFileName = p_oldDirectoryPath+fileName;
                                oldFile =new File(oldFileName);
                                newFileName=oldFileName.replace(p_oldDirectoryPath,p_finalDirectoryPath);
                                newFile=new File(newFileName);
                                if(oldFile!=null)
                                {
                                    oldFile.renameTo(newFile);
									if (_logger.isDebugEnabled())
                                                _logger.debug("moveFilesToFinalDirectory"," File "+oldFileName+" is moved to "+newFileName);
                                }
                                else
                                {
                                        if (_logger.isDebugEnabled())
                                                _logger.debug("moveFilesToFinalDirectory"," File"+oldFileName+" is null");
                                }

                        /*if(oldDir.exists())
                            oldDir.delete();*/
                    _logger.debug("moveFilesToFinalDirectory"," File "+oldFileName+" is moved to "+newFileName);
                }
                catch(Exception e)
                {
                _logger.error("moveFilesToFinalDirectory", "Exception " + e.getMessage());
                _logger.errorTrace(METHOD_NAME,e);
            EventHandler.handle(EventIDI.SYSTEM_ERROR,EventComponentI.SYSTEM,EventStatusI.RAISED,EventLevelI.FATAL,"DWHFileCreation[moveFilesToFinalDirectory]","","","","Exception:"+e.getMessage());
                    throw new BTSLBaseException("DWHFileCreation","deleteAllFiles",PretupsErrorCodesI.DWH_ERROR_EXCEPTION);
                }
        finally
        {
                        if(oldFile!=null) oldFile = null;
                        if(newFile!=null) newFile = null;
                        if(parentDir!=null) parentDir = null;
                        if(newDir!=null) newDir = null;
                        if(oldDir!=null) oldDir = null;
            if (_logger.isDebugEnabled())
                _logger.debug("moveFilesToFinalDirectory", "Exiting.. ");
        } // end of finally
        }
		
 public static String createDirectory()
        {

                String dir = Constants.getProperty("FinalUploadBatchUserChangeStatusFilePath");
                String dirName=null;
                String completeFinalDirPath=null;
                try{
                        dirName=(((BTSLUtil.getDateTimeStringFromDate(new Date())).replace("/","")).replace(":","")).replace(" ","");
                        completeFinalDirPath=dir+dirName;
                        File file = new File(completeFinalDirPath);
                        if (!file.exists()) {
                                if (file.mkdir()) {
                                        System.out.println("Directory is created!");
                                } else {
                                        System.out.println("Failed to create directory!");
                                }
                        }
                }
                catch(ParseException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                         if (_logger.isDebugEnabled())
                        _logger.debug("createDirectory", "Exiting.. finalDirectoryName :: "+completeFinalDirPath);
                        return completeFinalDirPath;
                }

        }



                                                                                                                           