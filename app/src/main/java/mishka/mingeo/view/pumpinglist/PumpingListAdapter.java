package mishka.mingeo.view.pumpinglist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Pumping;

public class PumpingListAdapter extends RecyclerView.Adapter<PumpingListAdapter.PumpingItemViewHolder> {

    private List<Pumping> pumpings;
    private PumpingListActivity pumpingListActvity;

    @Inject
    public PumpingListAdapter() {
        pumpings = new ArrayList<>();
    }

    @Override
    public PumpingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pumping_item, parent, false);
        return new PumpingItemViewHolder(view, pumpingListActvity);
    }

    @Override
    public void onBindViewHolder(PumpingItemViewHolder holder, int position) {
        holder.setPumping(pumpings.get(position));
    }

    @Override
    public int getItemCount() {
        return pumpings.size();
    }

    public void updatePumpings(List<Pumping> pumpings) {
        this.pumpings = pumpings;
        notifyDataSetChanged();
    }

    public void setPumpingListActivity(PumpingListActivity pumpingListActivity) {
        this.pumpingListActvity = pumpingListActivity;
    }

    static class PumpingItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pumping_name)
        TextView pumpingName;

        private Pumping pumping;
        private PumpingListActivity pumpingListActivity;

        public PumpingItemViewHolder(View itemView, PumpingListActivity pumpingListActivity) {
            super(itemView);
            this.pumpingListActivity = pumpingListActivity;

            pumpingName = itemView.findViewById(R.id.pumping_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PumpingItemViewHolder.this.pumpingListActivity.onPumpingSelected(pumping);
                }
            });
        }

        void setPumping(Pumping pumping){
            this.pumping = pumping;
            pumpingName.setText("Выкачка #" + pumping.getId());
        }
    }
}
