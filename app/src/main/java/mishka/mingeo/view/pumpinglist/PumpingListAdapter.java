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
import butterknife.ButterKnife;
import mishka.mingeo.R;
import mishka.mingeo.data.Pumping;

public class PumpingListAdapter extends RecyclerView.Adapter<PumpingListAdapter.PumpingItemViewHolder> {

    private List<Pumping> pumpings;

    @Inject
    public PumpingListAdapter() {
        pumpings = new ArrayList<>();
    }

    @Override
    public PumpingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pumping_item, parent, false);
        return new PumpingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PumpingItemViewHolder holder, int position) {
        holder.setPumpingName("Выкачка #" + pumpings.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return pumpings.size();
    }

    public void updatePumpings(List<Pumping> pumpings) {
        System.out.println("update pumpings: " + pumpings.size());
        this.pumpings = pumpings;
        notifyDataSetChanged();
    }

    static class PumpingItemViewHolder extends RecyclerView.ViewHolder {

        private TextView pumpingName;

        public PumpingItemViewHolder(View itemView) {
            super(itemView);
            pumpingName = itemView.findViewById(R.id.pumping_name);
        }

        void setPumpingName(String name){
            pumpingName.setText(name);
        }
    }
}
