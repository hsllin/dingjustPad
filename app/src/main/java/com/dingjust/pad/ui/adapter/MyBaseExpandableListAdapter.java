package com.dingjust.pad.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.dingjust.pad.R;

import java.util.List;

/**
 * @author huangsonglin
 * @version 1.0
 * @since JDK1.8
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    private List<String> groupNameList;
    private List<List<String>> childNameList;
    private Handler mainHandler;
    private Thread mthread;
    private boolean flag;
    private int count = 50;

    public MyBaseExpandableListAdapter(Context mContext, List<String> groupNameList, List<List<String>> childNameList) {
        this.mContext = mContext;
        this.groupNameList = groupNameList;
        this.childNameList = childNameList;
    }


    @Override
    public int getGroupCount() {
        return groupNameList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public String getGroup(int groupPosition) {
        return groupNameList.get(groupPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //利用viewHolder来进行缓存
        ParentViewHolder viewHolder = null;

        //获取父组的布局
        if (convertView == null) {
            viewHolder = new ParentViewHolder();
            convertView = View.inflate(mContext, R.layout.item_group_name, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.group_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ParentViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(getGroup(groupPosition));
        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
//        return childNameList.get(groupPosition).size();
        return 1;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /*@Override
    public String getChild(int groupPosition, int childPosition) {
        return childNameList.get(groupPosition).get(childPosition);
    }*/
    @Override
    public List<String> getChild(int groupPosition, int childPosition) {
        return childNameList.get(groupPosition);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder viewHolder = null;

        //获取父组的布局
        if (convertView == null) {
            viewHolder = new ChildViewHolder();
            convertView = View.inflate(mContext, R.layout.item_child_name, null);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.child_name);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.child_name2);
            viewHolder.textView3 = (TextView) convertView.findViewById(R.id.child_name3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        viewHolder.textView1.setText(getChild(groupPosition, childPosition).get(0));
        viewHolder.textView2.setText(getChild(groupPosition, childPosition).get(1));
        viewHolder.textView3.setText(getChild(groupPosition, childPosition).get(2));
        /*获取子item的布局*/
//        TextView childName = (TextView) convertView.findViewById(R.id.child_name);
//        TextView childName2 = (TextView) convertView.findViewById(R.id.child_name2);
//        TextView childName3 = (TextView) convertView.findViewById(R.id.child_name3);
//        String text1 = getChild(groupPosition, childPosition).get(0);
//        childName.setText(getChild(groupPosition, childPosition).get(0));
//        childName2.setText(getChild(groupPosition, childPosition).get(1));
//        childName3.setText(getChild(groupPosition, childPosition).get(2));

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /* public List<String> getParentList(){
         List<String> parentList=new ArrayList<>();
         String address="http://169.254.243.192:8080/jobContent.json";
         HttpUtil.sendOkHttpRequest(address, new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {

             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {

             }
         });
         return parentList;
     }*/
    //holder用来储存textview的信息，可重用，便于局部更新数据
    public final class ParentViewHolder {
        public TextView textView;
    }

    public final class ChildViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
    }
//TODO:改变expandableListview中某个group的内容
    public void updateView(int position, ExpandableListView listView ) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int lastVisiblePosition = listView.getLastVisiblePosition();
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            TextView view = (TextView) listView.getChildAt(0).findViewById(R.id.child_name2);
            String message = view.getText().toString();
           Log.d("","");


        }

    }


}
