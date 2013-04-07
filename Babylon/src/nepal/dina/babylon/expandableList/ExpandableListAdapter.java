package nepal.dina.babylon.expandableList;


import java.util.List;

import nepal.dina.babylon.MainActivity;
import nepal.dina.babylon.MainFragment;
import nepal.dina.babylon.Mapper;
import nepal.dina.babylon.R;
import nepal.dina.babylon.ResourceMapper;
import nepal.dina.babylon.ResourceMapper.Resource;
import nepal.dina.babylon.expandableList.GroupEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class ExpandableListAdapter extends  BaseExpandableListAdapter{

	private Context mContext;
	private ExpandableListView mExpandableListView;
	private List<GroupEntity> mGroupCollection;
	public int[] groupStatus;
	
	public String level;
	public String language;
	private Resource selected;
	
	

	public ExpandableListAdapter(Context pContext,
			ExpandableListView pExpandableListView,
			List<GroupEntity> pGroupCollection) {
		mContext = pContext;
		mGroupCollection = pGroupCollection;
		mExpandableListView = pExpandableListView;
		groupStatus = new int[mGroupCollection.size()];

		setListEvent();
		
		selected = Resource.LVL;
		
	}

	private void setListEvent() {

		mExpandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {

					public void onGroupExpand(int arg0) {
						// TODO Auto-generated method stub
						groupStatus[arg0] = 1;
					}
				});

		mExpandableListView
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {

					public void onGroupCollapse(int arg0) {
						// TODO Auto-generated method stub 
						groupStatus[arg0] = 0;
					}
				});
		mExpandableListView.setOnChildClickListener(new OnChildClickListener() {
			
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				parent.collapseGroup(groupPosition);
				mExpandableListView.collapseGroup(groupPosition);
				return true;
			}
		});
	}

	public String getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return mGroupCollection.get(arg0).GroupItemCollection.get(arg1).Name;
	}

	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getChildView(final int arg0, final int arg1, boolean arg2, View arg3,
			final ViewGroup arg4) {
		// TODO Auto-generated method stub

		final ChildHolder childHolder;
		if (arg3 == null) {
			arg3 = LayoutInflater.from(mContext).inflate(
					R.layout.list_group_item, null);
			
			

			childHolder = new ChildHolder();

			childHolder.title = (ImageView) arg3.findViewById(R.id.item_title);
			
			//((TextView)childHolder.title).set
			arg3.setTag(childHolder);
		}else {
			childHolder = (ChildHolder) arg3.getTag();
		}

		childHolder.title.setImageResource(ResourceMapper.getResource(arg0, arg1).getChildDrawable()); // Text(mGroupCollection.get(arg0).GroupItemCollection.get(arg1).Name);
		
		childHolder.title.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				mExpandableListView.collapseGroup(arg0);
				//TODO
				if(arg0==0){
					String s =  mGroupCollection.get(arg0).GroupItemCollection.get(arg1).Name;
					((MainActivity)mContext).language = Mapper.getLanguage(s);
					
					//((GroupEntity)mExpandableListView.getItemAtPosition(0)).Name = "novo";
					
					//((RelativeLayout) getGroupView(0, true, null, null)).setBackgroundResource(R.drawable.lng_btn1_bck);
					
					//((GroupHolder)((RelativeLayout) getGroupView(0, true, null, null)).getTag()).img.setBackgroundResource(R.drawable.lng_btn1_bck);// = (ImageView) arg2.findViewById(R.id.tag_img);
					
					
					//((GroupHolder)((RelativeLayout) getGroupView(0, true, null, null)).getTag()).title.setImageResource(slected);
					
					
				}
				else{
					String s =  mGroupCollection.get(arg0).GroupItemCollection.get(arg1).Name;
					((MainActivity)mContext).level =  Mapper.getLevel(s);
				}
				selected = ResourceMapper.getResource(arg0, arg1);
				//slected = R.drawable.group_up;
			}
		});
		
		return arg3;
	}
	
	

	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return mGroupCollection.get(arg0).GroupItemCollection.size();
	}

	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return mGroupCollection.get(arg0);
	}

	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mGroupCollection.size();
	}

	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getGroupView(int arg0, boolean arg1, View arg2,  ViewGroup arg3) {
		// TODO Auto-generated method stub
		GroupHolder groupHolder;
		if (arg2 == null) {
			arg2 = LayoutInflater.from(mContext).inflate(R.layout.list_group,
					null);
			groupHolder = new GroupHolder();
			groupHolder.img = (ImageView) arg2.findViewById(R.id.tag_img);
			groupHolder.title = (ImageView) arg2.findViewById(R.id.group_title);
			arg2.setTag(groupHolder);
		} else {
			groupHolder = (GroupHolder) arg2.getTag();
		}
		if (groupStatus[arg0] == 0) {
			groupHolder.img.setImageResource(R.drawable.group_down);
		} else {
			groupHolder.img.setImageResource(R.drawable.group_up);
		}
		if(selected.getParent() == arg0)
		groupHolder.title.setImageResource(selected.getParentDrawable());
		
		//slected = R.drawable.lvl_a;

		return arg2;
	}

	class GroupHolder {
		ImageView img;
		ImageView title;
	}

	class ChildHolder {
		ImageView title;
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
