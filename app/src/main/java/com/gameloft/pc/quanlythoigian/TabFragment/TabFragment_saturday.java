package com.gameloft.pc.quanlythoigian.TabFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.gameloft.pc.quanlythoigian.classPackage.CustomAdapter;
import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.classPackage.monHoc;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment_saturday extends Fragment {


    ListView lvMonHoc;
    ImageButton btnAdd;

    public TabFragment_saturday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_fragment_saturday, container, false);

        lvMonHoc = (ListView) view.findViewById(R.id.lvMonHoc);
        btnAdd = (ImageButton) view.findViewById(R.id.btnAdd);

        ArrayList<monHoc> arrayList = new ArrayList<>();
        monHoc monHoc1 = new monHoc("Cong nghe di dong", "9:09", "F101");
        monHoc monHoc2 = new monHoc("Lap trinh Java", "9:14", "F301");
        monHoc monHoc3 = new monHoc("Lap trinh huong doi tuong", "9:12", "F410");
        monHoc monHoc4 = new monHoc("Xu li tin hieu so", "10:5", "H307");
        monHoc monHoc5 = new monHoc("Tu tuong HCM", "10:5", "H307");
        monHoc monHoc6 = new monHoc("Co so du lieu", "10:5", "E510");
        monHoc monHoc7 = new monHoc("The duc", "10:5", "H307");
        monHoc monHoc8 = new monHoc("Tu hoc", "10:5", "G892");

        arrayList.add(monHoc1);
        arrayList.add(monHoc2);
        arrayList.add(monHoc3);
        arrayList.add(monHoc4);
        arrayList.add(monHoc5);
        arrayList.add(monHoc6);
        arrayList.add(monHoc7);
        arrayList.add(monHoc8);
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), R.layout.dong_listview, arrayList);
        lvMonHoc.setAdapter(customAdapter);

        return view;
    }

}
