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
import com.gameloft.pc.quanlythoigian.classPackage.CustomAdapter;
import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;
import com.gameloft.pc.quanlythoigian.detailscr;
import com.gameloft.pc.quanlythoigian.editscr;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment_sunday extends Fragment {


    ListView lvMonHoc;
    ImageButton btnAdd;
    List<MonHoc> listMonHoc;
    DatabaseAdapter database;
    CustomAdapter customAdapter;
    View view;

    public static final int REQUEST_CODE_ADD = 1;
    public static final int REQUEST_CODE_EDIT = 2;

    public static final int RESULT_CODE_ADD = 3;
    public static final int RESULT_CODE_EDIT = 4;

    public TabFragment_sunday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_fragment_sunday, container, false);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

        return view;
    }

    private void init(){
        listMonHoc =  new ArrayList<MonHoc>();
        database = new DatabaseAdapter(TabFragment_sunday.super.getContext());
    }

    private void getWidgets(){
        lvMonHoc = (ListView) view.findViewById(R.id.lvMonHoc);
        btnAdd = (ImageButton) view.findViewById(R.id.btnAdd);

    }

    private void setWidgets(){
        database.open();
        listMonHoc = database.getData(8);

        for(int i=0;i<listMonHoc.size()-1;i++){
            if(timeConvert(listMonHoc.get(i).getThoiGian2()) > timeConvert(listMonHoc.get(i+1).getThoiGian1())){
                listMonHoc.get(i).setWarning(true);
                listMonHoc.get(i+1).setWarning(true);
            }
        }

        customAdapter = new CustomAdapter(getActivity(), R.layout.dong_listview, listMonHoc);
        lvMonHoc.setAdapter(customAdapter);
        registerForContextMenu(lvMonHoc);
    }

    private void addWidgetsListener() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabFragment_sunday.super.getContext(),AddActivity.class);
                startActivityForResult(intent,REQUEST_CODE_ADD);
            }
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TabFragment_sunday.super.getContext(),detailscr.class);
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
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            switch (item.getItemId()){
                case R.id.itDelete:
                    final MonHoc monHoc = listMonHoc.get(menuInfo.position);
                    AlertDialog.Builder rm=new AlertDialog.Builder(TabFragment_sunday.super.getActivity());
                    rm.setMessage("Bạn có chắc chắn muốn xóa ?");
                    rm.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    rm.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean check = database.delete(monHoc,8);
                            if(check){
                                customAdapter.remove(monHoc);
                                customAdapter.notifyDataSetChanged();
                                Toast.makeText(TabFragment_sunday.super.getActivity(),"Đã xóa !",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(TabFragment_sunday.super.getActivity(),"Sorry! Lỗi cập nhật dữ liệu.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    rm.create().show();
                    return true;
                case R.id.itEdit:
                    Intent iEdit = new Intent(TabFragment_sunday.super.getContext(),editscr.class);
                    MonHoc monHocEdit = listMonHoc.get(menuInfo.position);
                    iEdit.putExtra("monhocEdit",monHocEdit);
                    startActivityForResult(iEdit,REQUEST_CODE_EDIT);
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
                    boolean check = database.AddMonHoc(monHoc,8);
                    if(check){
                        listMonHoc.add(monHoc);
                        customAdapter.notifyDataSetChanged();
                        Toast.makeText(TabFragment_sunday.super.getActivity(),"Đã thêm môn học mới !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TabFragment_sunday.super.getActivity(),"Sorry! Lỗi cập nhật dữ liệu.",Toast.LENGTH_SHORT).show();
                    }
                    //database.close();
            }
        }

        if(requestCode == REQUEST_CODE_EDIT){
            switch (resultCode){
                case RESULT_CODE_EDIT:
                    MonHoc monHoc = (MonHoc) data.getSerializableExtra("monhocEdited");
                    boolean check = database.update(monHoc,8);
                    if(check){
                        listMonHoc = database.getData(8);
                        customAdapter = new CustomAdapter(getActivity(), R.layout.dong_listview, listMonHoc);
                        lvMonHoc.setAdapter(customAdapter);
                        Toast.makeText(TabFragment_sunday.super.getActivity(),"Cập nhật thành công !",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TabFragment_sunday.super.getActivity(),"Sorry! Lỗi cập nhật dữ liệu.",Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }
    public int timeConvert(String time){
        String[] strings = time.split(":");
        return (Integer.valueOf(strings[0].trim())*60 + Integer.valueOf(strings[1].trim()));
    }
}
