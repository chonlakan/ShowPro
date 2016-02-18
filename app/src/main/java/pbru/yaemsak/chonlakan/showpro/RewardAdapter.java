package pbru.yaemsak.chonlakan.showpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chonlakan on 18/2/2559.
 */
public class RewardAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private int[] iconInts;
    private String[] nameRewardStrings,pointRewardStrings;

    public RewardAdapter(Context context, int[] iconInts, String[] nameRewardStrings, String[] pointRewardStrings) {
        this.context = context;
        this.iconInts = iconInts;
        this.nameRewardStrings = nameRewardStrings;
        this.pointRewardStrings = pointRewardStrings;
    }//constructor

    @Override
    public int getCount() {
        return nameRewardStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.reward_listview, viewGroup, false);

        ImageView iconImageView = (ImageView) view.findViewById(R.id.imageView2);
        iconImageView.setImageResource(iconInts[i]);

        TextView nameRewardTextView = (TextView) view1.findViewById(R.id.textView12);
        nameRewardTextView.setText(nameRewardStrings[i]);

        TextView pointRewardTextView = (TextView) view1.findViewById(R.id.textView13);
        pointRewardTextView.setText(pointRewardStrings[i]);

        return view1;
    }
}//main class
