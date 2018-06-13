package mishka.mingeo.view.pumping.borehole;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import mishka.mingeo.R;

public class AddDepthDialog extends DialogFragment {

    private AddDepthDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Новый замер");

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.add_depth_dialog, null);
        final EditText etDepth = dialogView.findViewById(R.id.depth);

        builder.setView(dialogView);

        builder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(etDepth.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Введено недопустимое значение", Toast.LENGTH_SHORT).show();
                    return;
                }
                int depth = Integer.parseInt(etDepth.getText().toString());
                listener.onDepthSet(depth);
            }
        });

        return builder.create();
    }

    public void setListener(AddDepthDialogListener listener) {
        this.listener = listener;
    }

    interface AddDepthDialogListener{
        void onDepthSet(int depth);
    }
}
