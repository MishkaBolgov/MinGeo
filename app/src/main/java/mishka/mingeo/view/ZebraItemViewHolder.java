package mishka.mingeo.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import mishka.mingeo.R;

public abstract class ZebraItemViewHolder extends RecyclerView.ViewHolder {
    public ZebraItemViewHolder(View itemView) {
        super(itemView);
    }

    public void setPositionOddityDependingBackground(int position) {
//        if (position % 2 == 0)
//            super.itemView.setBackgroundResource(R.color.oddListBackground);
//        else super.itemView.setBackgroundResource(R.color.evenListBackground);
    }
}
