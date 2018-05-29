package mishka.mingeo.view.pumping.borehole;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mishka.mingeo.R;
import mishka.mingeo.data.Borehole;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoreholeFragment extends Fragment {
    private Borehole borehole;


    public BoreholeFragment() {
        // Required empty public constructor
    }

    public void setBorehole(Borehole borehole) {
        this.borehole = borehole;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borehole, container, false);
        return view;
    }

}
