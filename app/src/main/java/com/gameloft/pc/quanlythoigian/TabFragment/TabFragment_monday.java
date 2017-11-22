package com.gameloft.pc.quanlythoigian.TabFragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.gameloft.pc.quanlythoigian.AddActivity;
import com.gameloft.pc.quanlythoigian.MyDatabase.DatabaseAdapter;
import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.camActiv;
import com.gameloft.pc.quanlythoigian.classPackage.CustomAdapter;
import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;
import com.gameloft.pc.quanlythoigian.detailscr;
import com.gameloft.pc.quanlythoigian.editscr;
import com.gameloft.pc.quanlythoigian.notescr;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment_monday extends Fragment{

    ListView lvMonHoc;
    ImageButton btnAdd;
    List<MonHoc> listMonHoc;
    DatabaseAdapter database;
    CustomAdapter customAdapter;
    View view;

    public static final int REQUEST_CODE_ADD = 1;
    public static final int REQUEST_CODE_EDIT = 2;
    public static final int REQUEST_CODE_NOTE = 3;
    public static final int REQUEST_CODE_CAM = 7;

    public static final int RESULT_CODE_ADD = 4;
    public static final int RESULT_CODE_EDIT = 5;
    public static final int RESULT_CODE_NOTE = 6;
    public static final int RESULT_CODE_CAM = 8;



    public TabFragment_monday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_fragment_monday, container, false);
        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

        return view;
    }


    private void init(){
        listMonHoc =  new ArrayList<>();
        database = new DatabaseAdapter(TabFragment_monday.super.getContext());
    }

    private void getWidgets(){
        lvMonHoc = (ListView) view.findViewById(R.id.lvMonHoc);
        btnAdd = (ImageButton) view.findViewById(R.id.btnAdd);

    }

    private void setWidgets(){
        database.open();
        listMonHoc = database.getData(2);

        customAdapter = new CustomAdapter(getActivity(), R.layout.dong_listview, listMonHoc);
        lvMonHoc.setAdapter(customAdapter);
        registerForContextMenu(lvMonHoc);
    }

    private void addWidgetsListener() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabFragment_monday.super.getContext(),AddActivity.class);
                startActivityForResult(intent,REQUEST_CODE_ADD);
            }
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TabFragment_monday.super.getContext(),detailscr.class);
                MonHoc monHoc;
                monHoc = listMonHoc.get(position);
                intent.putExtra("chitietmonhoc",monHoc);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(getUserVisibleHint()){
            final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            switch (item.getItemId()){
                case R.id.itDelete:
                    final MonHoc monHoc = listMonHoc.get(menuInfo.position);
                    AlertDialog.Builder rm=new AlertDialog.Builder(TabFragment_monday.super.getActivity());
                    rm.setMessage(R.string.ban_co_chac_muon_xoa);
                    rm.setNegativeButton(R.string.huy, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    rm.setPositiveButton(R.string.xoa, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final boolean check = database.delete(monHoc,2);
                            if(check){
                                customAdapter.remove(monHoc);
                                customAdapter.notifyDataSetChanged();
                                Toast.makeText(TabFragment_monday.super.getActivity(),R.string.da_xoa,Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(TabFragment_monday.super.getActivity(),R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    rm.create().show();
                    return true;

                case R.id.itEdit:
                    Intent iEdit = new Intent(TabFragment_monday.super.getContext(),editscr.class);
                    MonHoc monHocEdit = listMonHoc.get(menuInfo.position);
                    iEdit.putExtra("monhocEdit",monHocEdit);
                    startActivityForResult(iEdit,REQUEST_CODE_EDIT);
                    return true;

                case R.id.itNote:
                    Intent iNote = new Intent(TabFragment_monday.super.getContext(),notescr.class);
                    MonHoc monHocNote = listMonHoc.get(menuInfo.position);
                    iNote.putExtra("monHocNote",monHocNote);
                    startActivityForResult(iNote,REQUEST_CODE_NOTE);
                    return true;

                case R.id.itCam:
                    Intent iCam = new Intent(TabFragment_monday.super.getContext(),camActiv.class);
                    MonHoc monHocCam = listMonHoc.get(menuInfo.position);
                    iCam.putExtra("monHocCam",monHocCam);
                    startActivityForResult(iCam,REQUEST_CODE_CAM);
                    return true;
            }
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD){
            switch (resultCode){
                case RESULT_CODE_ADD:
                    MonHoc monHoc = (MonHoc) data.getSerializableExtra("monhoc");
                    boolean check = database.AddMonHoc(monHoc,2);
                    if(check){
                        listMonHoc.add(monHoc);
                        customAdapter.notifyDataSetChanged();
                        Toast.makeText(TabFragment_monday.super.getActivity(),R.string.da_them_mon_hoc_moi,Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TabFragment_monday.super.getActivity(),R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
                    }
                    //database.close();
            }
        }

        if(requestCode == REQUEST_CODE_EDIT){
            switch (resultCode){
                case RESULT_CODE_EDIT:
                    MonHoc monHoc = (MonHoc) data.getSerializableExtra("monhocEdited");
                    boolean check = database.update(monHoc,2);
                    if(check){
                        listMonHoc = database.getData(2);
                        customAdapter = new CustomAdapter(getActivity(), R.layout.dong_listview, listMonHoc);
                        lvMonHoc.setAdapter(customAdapter);
                        Toast.makeText(TabFragment_monday.super.getActivity(),R.string.cap_nhat_thanh_cong,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TabFragment_monday.super.getActivity(),R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
                    }
            }
        }

        if(requestCode == REQUEST_CODE_NOTE){
            switch (resultCode){
                case RESULT_CODE_NOTE:
                    MonHoc monHoc = (MonHoc) data.getSerializableExtra("monHocNoted");
                    boolean check = database.update(monHoc,2);
                    if(check){
                        listMonHoc = database.getData(2);
                        customAdapter = new CustomAdapter(getActivity(),R.layout.dong_listview, listMonHoc);
                        lvMonHoc.setAdapter(customAdapter);
                        Toast.makeText(TabFragment_monday.super.getActivity(),R.string.da_luu_ghi_chu, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TabFragment_monday.super.getActivity(),R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
                    }
            }
        }

        if (requestCode == REQUEST_CODE_CAM){
            switch (resultCode){
                case RESULT_CODE_CAM:
                    MonHoc monHoc = (MonHoc) data.getSerializableExtra("monHocCamed");
                    boolean check = database.update(monHoc,2);
                    if(check){
                        listMonHoc = database.getData(2);
                        customAdapter = new CustomAdapter(getActivity(),R.layout.dong_listview, listMonHoc);
                        lvMonHoc.setAdapter(customAdapter);
                        Toast.makeText(TabFragment_monday.super.getActivity(),R.string.da_luu_anh, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TabFragment_monday.super.getActivity(),R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }
}
