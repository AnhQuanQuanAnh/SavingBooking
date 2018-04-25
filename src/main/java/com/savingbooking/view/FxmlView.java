package com.savingbooking.view;

import java.util.ResourceBundle;

public enum FxmlView {

	DASHBOARD {

		@Override
		public String getTitle() {
			return getStringFromResourceBundle("dashboard.title");

		}

		@Override
		public String getFxmlFile() {
			return "/fxml/DashBoard.fxml";
		}

	},
	USER {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("user.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/User.fxml";
		}
	},
	LOGIN {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("login.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/Login.fxml";
		}
	},
	SAVINGBOOK {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("savingbook.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/SavingBook.fxml";
		}
	},
	DEPOSITCARD {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("depositcard.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/DepositCard.fxml";
		}
	},
	WITHDRAWCARD {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("withdrawcard.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/WithdrawCard.fxml";
		}
	},
	REPORT {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("report.title");

		}

		@Override
		public String getFxmlFile() {
			return "/fxml/Report.fxml";
		}
	};

	public abstract String getTitle();

	public abstract String getFxmlFile();

	String getStringFromResourceBundle(String key) {
		return ResourceBundle.getBundle("Bundle").getString(key);
	}

}
