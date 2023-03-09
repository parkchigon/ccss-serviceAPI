package com.lgu.ccss.api.conf.model;

public class ResultConfSystemJSON {
    public class AIConf {
    	private String server;

		public String getServer() {
			return server;
		}

		public void setServer(String server) {
			this.server = server;
		}

		@Override
		public String toString() {
			return "AIConf [server=" + server + "]";
		}
    }
    
    public class LogConf {
    	private String server;
    	private String pushLogSize;
    	
		public String getServer() {
			return server;
		}
		public void setServer(String server) {
			this.server = server;
		}
		public String getPushLogSize() {
			return pushLogSize;
		}
		public void setPushLogSize(String pushLogSize) {
			this.pushLogSize = pushLogSize;
		}
		
		@Override
		public String toString() {
			return "LogConf [server=" + server + ", pushLogSize=" + pushLogSize + "]";
		}
    }

	private AIConf ai = new AIConf();
	private LogConf log = new LogConf();
	
	public AIConf getAi() {
		return ai;
	}

	public void setAi(AIConf ai) {
		this.ai = ai;
	}

	public LogConf getLog() {
		return log;
	}

	public void setLog(LogConf log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "ResultConfSystemJSON [ai=" + ai + ", log=" + log + "]";
	}
}
