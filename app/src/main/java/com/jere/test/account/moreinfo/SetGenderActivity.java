package com.jere.test.account.moreinfo;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jere.test.R;

import java.util.ArrayList;

/**
 * @author jere
 */
public class SetGenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_gender);

        ArrayList<String> genderList = new ArrayList<>();
        genderList.add("男");
        genderList.add("女");

        Spinner selectGenderSpinner = findViewById(R.id.select_gender_spinner);
        MySpinnerAdapter mySpinnerAdapter = new MySpinnerAdapter(this, genderList);
        selectGenderSpinner.setAdapter(mySpinnerAdapter);
        selectGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gender = (String) parent.getItemAtPosition(position);
                Toast.makeText(SetGenderActivity.this, gender, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private class MySpinnerAdapter implements SpinnerAdapter {
        Context context;
        ArrayList<String> genderList;

        MySpinnerAdapter(Context context, ArrayList<String> arrayList) {
            this.context = context;
            genderList = arrayList;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.set_gender_spinner_drop_down_item_view, null);
            }
            TextView genderTv = convertView.findViewById(R.id.gender_tv);
            String genderString = (String) getItem(position);
            genderTv.setText(genderString);

            return convertView;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getCount() {
            return genderList.size();
        }

        @Override
        public Object getItem(int position) {
            return genderList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.set_gender_spinner_drop_down_item_view, null);
            }
            TextView genderTv = convertView.findViewById(R.id.gender_tv);
            String genderString = (String) getItem(position);
            genderTv.setText(genderString);

            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return genderList.isEmpty();
        }
    }
}
