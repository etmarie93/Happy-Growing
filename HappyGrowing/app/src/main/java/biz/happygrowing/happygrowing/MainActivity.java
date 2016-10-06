package biz.happygrowing.happygrowing;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by alwaysluck on 9/21/2016.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }
    @Override
    //To create the main menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.planner_actions, menu);


        /*MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean OnMenuItemActionCollapse(MenuItem actionDraw){
                return true; //to collapse action view
            }
            @Override
            public boolean onMenuItemActionExpand(MenuItem action_draw) {
                //Do something when expanded
                return true; //to expand action view
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                return false;
            }
        };*/
    }

    //To make contextual action bar
    //https://developer.android.com/guide/topics/ui/menus.html#context-menu
    //This only creates ONE action bar... maybe need to start a new fragment
    //Every time an options menu item is clicked
    private ActionMode.Callback DrawAction = new ActionMode.Callback(){
        //called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu){
            //Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_draw, menu);
            return true;
        }
        //Called each time action mode is shown. Always called after onCreateActionMode, but
        //may be called multiple times if mode is invalidated
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu item){
           return false; //Return false if nothing is done
        }
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item){
            switch(item.getItemId()){
                case R.id.need_id:
                    //do the thing
                    mode.finish();//Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }
        //Called when user exists the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode){
            mActionMode = null;
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_draw:
                //enable contextual action mode
                if (mActionMode != null){
                    return false;
                }
                //Start the CAB using the ActionMode.Callback
                mActionMode = getActivity().startActionMode(DrawAction);
                view.setSelected(true);
                return true;
                break;
            case R.id.action_plants:
                return true;
            case R.id.action_resize:
                return true;
            case R.id.action_shade_map:
                return true;
            case R.id.action_share:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
