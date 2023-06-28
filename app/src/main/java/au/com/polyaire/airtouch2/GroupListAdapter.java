package au.com.polyaire.airtouch2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.data.GroupDataModel;
import java.io.PrintStream;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class GroupListAdapter extends ArrayAdapter<GroupDataModel> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ViewHolder {
        private ImageView fanDown;
        private ImageView fanUp;
        private TextView fanValue;
        private ImageView groupButton;
        private TextView groupName;
        private int position;
        private TextView program;
        private TextView separatorStatus;
        private TextView spillOrTurbo;

        private ViewHolder() {
        }
    }

    public GroupListAdapter(Context context, ArrayList<GroupDataModel> arrayList) {
        super(context, 0, arrayList);
    }

    protected int getGroupPosition(int i) {
        if (ExchData.isDualACMode() && ExchData.isDualDucted()) {
            PrintStream printStream = System.out;
            printStream.println("ac1 group num " + ExchData.getAC1().getGroupNumber());
            PrintStream printStream2 = System.out;
            printStream2.println("position " + i);
            return i >= ExchData.getAC1().getGroupNumber() ? i - 1 : i;
        }
        return i;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        final ViewHolder viewHolder;
        GroupDataModel item = getItem(i);
        if (view == null) {
            viewHolder = new ViewHolder();
            if (item.isSeparator()) {
                System.out.format("current seperator position is = %d", Integer.valueOf(i));
                view2 = LayoutInflater.from(getContext()).inflate(C0377R.layout.view_separator, viewGroup, false);
                viewHolder.separatorStatus = (TextView) view2.findViewById(C0377R.id.topspill1);
                viewHolder.separatorStatus.setText(ExchData.getAC2().getAcName());
                view2.setTag(viewHolder);
            } else {
                view2 = LayoutInflater.from(getContext()).inflate(C0377R.layout.view_listview, viewGroup, false);
                viewHolder.groupButton = (ImageView) view2.findViewById(C0377R.id.groupName);
                viewHolder.groupName = (TextView) view2.findViewById(C0377R.id.groupNameText);
                viewHolder.fanValue = (TextView) view2.findViewById(C0377R.id.fanValueDisplay);
                viewHolder.fanDown = (ImageView) view2.findViewById(C0377R.id.fanDown);
                viewHolder.fanUp = (ImageView) view2.findViewById(C0377R.id.fanUp);
                viewHolder.spillOrTurbo = (TextView) view2.findViewById(C0377R.id.spillOrTurbo);
                viewHolder.program = (TextView) view2.findViewById(C0377R.id.program);
                viewHolder.groupButton.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.GroupListAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        MessageInBytes messageInBytes = new MessageInBytes();
                        messageInBytes.SetZoneMessage(GroupListAdapter.this.getGroupPosition(viewHolder.position));
                        ExchData.sendMessage(messageInBytes.GetInBytes());
                    }
                });
                viewHolder.fanDown.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.GroupListAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        MessageInBytes messageInBytes = new MessageInBytes();
                        messageInBytes.SetFanMessage(GroupListAdapter.this.getGroupPosition(viewHolder.position), "DOWN");
                        ExchData.sendMessage(messageInBytes.GetInBytes());
                    }
                });
                viewHolder.fanUp.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.GroupListAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        MessageInBytes messageInBytes = new MessageInBytes();
                        messageInBytes.SetFanMessage(GroupListAdapter.this.getGroupPosition(viewHolder.position), "UP");
                        ExchData.sendMessage(messageInBytes.GetInBytes());
                    }
                });
                view2.setTag(viewHolder);
            }
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageView unused = viewHolder.fanDown;
        if (!item.isSeparator() && viewHolder.fanDown != null) {
            setGroupStatus(item, viewHolder);
        }
        viewHolder.position = i;
        return view2;
    }

    private void setGroupStatus(GroupDataModel groupDataModel, ViewHolder viewHolder) {
        viewHolder.groupName.setText(groupDataModel.getGroupName());
        viewHolder.fanValue.setText(String.valueOf(groupDataModel.getFanValue()));
        if (groupDataModel.isOn()) {
            viewHolder.groupButton.setImageResource(C0377R.C0378drawable.btnbase_name_s);
        } else {
            viewHolder.groupButton.setImageResource(C0377R.C0378drawable.btnbase_name);
        }
        if (groupDataModel.getProgramNum() > 0) {
            viewHolder.program.setVisibility(0);
            TextView textView = viewHolder.program;
            textView.setText("P " + String.valueOf(groupDataModel.getProgramNum()));
        } else {
            viewHolder.program.setVisibility(4);
        }
        if (!groupDataModel.isTurboGroup()) {
            if (groupDataModel.isSpill()) {
                viewHolder.spillOrTurbo.setVisibility(0);
                viewHolder.spillOrTurbo.setText("SPILL");
                viewHolder.spillOrTurbo.setTextColor(Color.parseColor("#FF9900"));
                return;
            }
            viewHolder.spillOrTurbo.setVisibility(4);
        } else if (groupDataModel.isTurboGroup()) {
            viewHolder.spillOrTurbo.setVisibility(0);
            viewHolder.spillOrTurbo.setText("TURBO");
            viewHolder.spillOrTurbo.setTextColor(Color.parseColor("#CCCCCC"));
            if (groupDataModel.isTurboIsOn()) {
                viewHolder.spillOrTurbo.setTextColor(Color.parseColor("#40749C"));
            } else if (groupDataModel.isSpill()) {
                viewHolder.spillOrTurbo.setText("SPILL");
                viewHolder.spillOrTurbo.setTextColor(Color.parseColor("#FF9900"));
            }
        }
    }
}
