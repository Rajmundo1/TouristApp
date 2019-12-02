package hu.bme.aut.android.touristapp.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import hu.bme.aut.android.touristapp.R;
import hu.bme.aut.android.touristapp.model.Content;

public class NewPlaceDialog extends AppCompatDialogFragment {
    //<!-- adapter.addItem(new Content("7", "HUN", "Budapest", "description", favorite,visited,desiretovisit);-->
    private EditText editTextCountry;
    private EditText editTextPlace;
    private EditText editTextDescription;
    private CheckBox checkBoxFavorite;
    private CheckBox checkBoxVisited;
    private CheckBox checkBoxDesireToVisit;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_place_dialog, null);

        builder.setView(view)
                .setTitle("New Place")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String country = editTextCountry.getText().toString();
                        String place = editTextPlace.getText().toString();
                        String desc = editTextDescription.getText().toString();
                        int favorite;
                        if(checkBoxFavorite.isChecked())
                            favorite = 1;
                        else
                            favorite = 0;

                        int visited;
                        if(checkBoxVisited.isChecked())
                            visited = 1;
                        else
                            visited = 0;

                        int desiretovisit;
                        if(checkBoxDesireToVisit.isChecked())
                            desiretovisit = 1;
                        else
                            desiretovisit = 0;


                        listener.addPlaceElement(new Content(country,place,desc,favorite,visited,desiretovisit));
                    }
                });

        editTextCountry = view.findViewById(R.id.edit_country);
        editTextPlace = view.findViewById(R.id.edit_place);
        editTextDescription = view.findViewById(R.id.edit_description);
        checkBoxFavorite = view.findViewById(R.id.favorite);
        checkBoxVisited = view.findViewById(R.id.visited);
        checkBoxDesireToVisit = view.findViewById(R.id.desidreToVisit);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void addPlaceElement(Content place);
    }
}