package apps.window.staticwindow.util;

import java.util.Vector;
import apps.window.util.tableModelUtil.TableUtils;
import util.cacheUtil.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.SearchPropertyWindow;
import beans.SearchConfig;
import beans.SearchProperty;
import beans.WindowSheet;
import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.SearchConfigConstants;
import constants.SearchPropertyConstants;
import constants.BeanConstants;

public class SearchPropertyWindowUtil extends BaseWindowUtil {
	SearchPropertyWindow searchpropertyWindow = null;
	SearchProperty searchproperty = null;
	String searchpropertyName;

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return SearchPropertyConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String searchpropertyName) {
		this.searchpropertyName = searchpropertyName;
	}

	/**
	 * @return the searchproperty
	 */
	public SearchProperty getSearchProperty() {
		return searchproperty;
	}

	/**
	 * @param searchproperty
	 *            the searchproperty to set
	 */
	public void setSearchProperty(SearchProperty searchproperty) {
		this.searchproperty = searchproperty;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		boolean flag = false;
		return validate(getSearchProperty(),
				SearchPropertyConstants.WINDOW_NAME);
	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub
		Property prop = searchpropertyWindow.propertyTable.getPropertyTable()
				.getSelectedProperty();
		if (action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			loadButtonAction();
		}
		if (action.equalsIgnoreCase(SearchPropertyConstants.SEARCHTEXTBOX)) {
			searchTextAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.RIGHTSIDECENTERTABLE)) {
			rightSideCenterTableAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.DELETEBUTTON)) {
			deleteButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.SAVEBUTTON)) {
			saveButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.HIERARACHICALTABLE)) {
			hierarachicalTableAction();
		}
		if (action
				.equalsIgnoreCase(SearchPropertyConstants.LOADALLSEARCHPROPERTY)) {
			loadButtonAction();
		}
	}

	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		searchpropertyWindow = (SearchPropertyWindow) windowName;
		setSearchProperty(searchpropertyWindow.getSearchProperty());
	}

	private void hierarachicalTableAction() {
		if (searchpropertyWindow.hierarchicalTable.getSelectedRow() != -1) {
			searchpropertyWindow.propertyTable
					.setPropertiesValues(searchpropertyWindow.model
							.getRow(searchpropertyWindow.hierarchicalTable
									.getSelectedRow()));
			searchpropertyWindow.setSearchProperty(searchpropertyWindow.model
					.getRow(searchpropertyWindow.hierarchicalTable
							.getSelectedRow()));
			setSearchProperty(searchpropertyWindow.model
					.getRow(searchpropertyWindow.hierarchicalTable
							.getSelectedRow()));
		}
	}

	private void saveButtonAction() {
		searchpropertyWindow.propertyTable.setfillValues(searchproperty);
		setSearchProperty((SearchProperty) searchpropertyWindow.propertyTable
				.getBean());
		// if(validate( ))
		// if(ReferenceDataCache.updateSearchProperty(getSearchProperty())) {
		// if(searchpropertyWindow.rightSideCenterTable.getSelectedRow() != -1)
		// {
		// int i= TableUtils.getSelectedRowIndex(
		// searchpropertyWindow.rightSideCenterTable);
		// searchpropertyWindow.model.udpateValueAt(getSearchProperty(), i, 0);
		// }
		// }
	}

	private void saveAsNewButtonAction() {
		SearchProperty searchproperty = new SearchProperty();
		searchpropertyWindow.propertyTable.setfillValues(searchproperty);
		setSearchProperty(searchproperty);
		// if(validate( )){
		// searchproperty =
		// ReferenceDataCache.saveSearchProperty(searchproperty);
		// searchpropertyWindow.model.addRow(getSearchProperty());
		// setSearchProperty(searchproperty);
		// }
	}

	private void newButtonAction() {
		searchpropertyWindow.propertyTable.clearPropertyValues();
		searchpropertyWindow.model.clear();
		setSearchProperty(null);
	}

	private void loadButtonAction() {
		newButtonAction();
		String searchText = searchpropertyWindow.searchpropertySearchTextField
				.getText();
		if (!commonUTIL.isEmpty(searchText)) {
			Vector<SearchProperty> data = null;// ReferenceDataCache.selectSearchPropertys(searchText);
			searchpropertyWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				SearchProperty firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					searchpropertyWindow.model.addRow((SearchProperty) data
							.get(i));
				}
				searchpropertyWindow.propertyTable
						.setPropertiesValues(firstRecord);
				setSearchProperty(firstRecord);
			}
		} else {
			Vector<SearchProperty> data = (Vector<SearchProperty>) ReferenceDataCache
					.selectALLData(BeanConstants.SEARCHPROPERTY);
			if (!commonUTIL.isEmpty(data)) {
				searchpropertyWindow.model.clear();
				SearchProperty firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					searchpropertyWindow.model.addRow((SearchProperty) data
							.get(i));
				}
				searchpropertyWindow.propertyTable
						.setPropertiesValues(firstRecord);
				setSearchProperty(firstRecord);
			}
		}
	}

	public void loadData(int id) {
		newButtonAction();
		Vector<SearchProperty> data = null;// ReferenceDataCache.getSearchProperty(id);
		searchpropertyWindow.model.clear();
		if (!commonUTIL.isEmpty(data)) {
			SearchProperty firstRecord = data.get(0);
			for (int i = 0; i < data.size(); i++) {
				searchpropertyWindow.model.addRow((SearchProperty) data.get(i));
			}
			searchpropertyWindow.propertyTable.setPropertiesValues(firstRecord);
			setSearchProperty(firstRecord);
		}
	}

	private void rightSideCenterTableAction() {
		if (searchpropertyWindow.rightSideCenterTable.getSelectedRow() != -1)
			searchpropertyWindow.propertyTable
					.setPropertiesValues(searchpropertyWindow.model
							.getRow(searchpropertyWindow.rightSideCenterTable
									.getSelectedRow()));
		searchpropertyWindow.setSearchProperty(searchpropertyWindow.model
				.getRow(searchpropertyWindow.rightSideCenterTable
						.getSelectedRow()));
		setSearchProperty(searchpropertyWindow.model
				.getRow(searchpropertyWindow.rightSideCenterTable
						.getSelectedRow()));
	}

	private void searchTextAction() {
		loadButtonAction();
	}

	// check Null pointerException.
	private void deleteButtonAction() {
		try {
			// if(ReferenceDataCache.deleteSearchProperty(searchproperty)) {
			// if( searchpropertyWindow.rightSideCenterTable.getSelectedRow() !=
			// -1) {
			// searchpropertyWindow.model.delRow(searchpropertyWindow.rightSideCenterTable.getSelectedRow());
			// }
			// setSearchProperty(null);
			// searchpropertyWindow.propertyTable.clearPropertyValues();
			// }
		} catch (Exception e) {
			commonUTIL.displayError(SearchPropertyConstants.WINDOW_NAME
					+ "Util", "deleteButtonAction", e);
		}
	}

	@Override
	public void windowstartUpData() {
           if(!commonUTIL.isEmpty(searchpropertyWindow.searchType)) {
        	   String sqlWhere = SearchConfigConstants.SQLKEYWHERE + "'" + searchpropertyWindow.searchType + "'";
        	 
        	   SearchConfig searchConfig = (SearchConfig) ReferenceDataCache.select(sqlWhere, BeanConstants.SEARCHCONFIG);
               setSearchConfig(searchConfig);
           }
	}
	
	private void setSearchConfig(SearchConfig searchConfig) {
		// TODO Auto-generated method stub
		this.searchConfig = searchConfig;
		
	}
	public SearchConfig	searchConfig = null;
	@Override
	public void clearALL() {
	}
}