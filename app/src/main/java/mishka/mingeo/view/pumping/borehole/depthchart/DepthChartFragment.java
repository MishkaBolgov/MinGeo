package mishka.mingeo.view.pumping.borehole.depthchart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import mishka.mingeo.R;
import mishka.mingeo.data.model.BoreholeDepth;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepthChartFragment extends Fragment implements DepthChartMvpView {

    @BindView(R.id.chart)
    LineChart chart;


    public DepthChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borehole_depth, container, false);

        ButterKnife.bind(this, view);

        view.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void update(List<BoreholeDepth> depths) {
        List<Entry> entries = new ArrayList<>();



        for (BoreholeDepth depth : depths) {
            entries.add(new Entry(depth.getMinutes(), depth.getDepth()));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Depth/Time");
        lineDataSet.setLineWidth(4);
        lineDataSet.setColor(getResources().getColor(R.color.colorSecondary));
        lineDataSet.setCircleColor(getResources().getColor(R.color.colorPrimary));

        LineData lineData = new LineData(lineDataSet);

        Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        chart.setData(lineData);
        chart.invalidate();

    }

    @Override
    public void showIfHidden() {
        if(getView().getVisibility() != View.VISIBLE)
            getView().setVisibility(View.VISIBLE);
    }
}
