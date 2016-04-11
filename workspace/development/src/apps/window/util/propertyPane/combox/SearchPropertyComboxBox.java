package apps.window.util.propertyPane.combox;

import apps.window.util.propertyPane.editor.SearchPropertyEditor;
import apps.window.util.propertyPane.panel.DateRulePropertyPanel;
import apps.window.util.propertyPane.panel.SearchPropertyPanel;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.combobox.AbstractComboBox.DefaultTextFieldEditorComponent;
import com.jidesoft.combobox.AbstractComboBox.EditorComponent;

public class SearchPropertyComboxBox extends AbstractComboBox {
	SearchPropertyEditor editor;
	String searchType = "";
	 public SearchPropertyComboxBox(SearchPropertyEditor searchPropertyEditor) {
		 setEditable(true);
	        initComponent();
	        this.editor = searchPropertyEditor;
	        setSearchType(searchPropertyEditor.getSearchType());
	    }
	 
	    public void setSearchType(String searchType) {
		// TODO Auto-generated method stub
	    	this.searchType = searchType;
	    	
		
	}
	    public String getSearchType( ) {
			// TODO Auto-generated method stub
		    	return editor.getSearchType();
		    	
			
		}
	    @Override
	    public boolean commitEdit() {
			 return super.commitEdit();    
		}
		public PopupPanel createPopupComponent() {
	        return new SearchPropertyPanel(this);
	    }

	    public EditorComponent createEditorComponent() {
	    	
	        return new DefaultTextFieldEditorComponent(String.class) {
	        	@Override
				public void setEditable(boolean arg0) {
					// TODO Auto-generated method stub
					super.setEditable(true);
				}
				
				protected String convertElementToString(Object value) {
					return (String)value;
				}
	        };
	    }
	    
	    public void showPopupPanelAsPopup(boolean show) {
	        super.showPopupPanelAsPopup(show);
	    }
	    
	    public void hidePopup(){
	    	setPopupVisible(false);
	    }
	    
	    public void setSelectedItem(Object val) {
	    	super.setSelectedItem(val);
	    }
}