package com.gameloft.pc.quanlythoigian.TabFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.gameloft.pc.quanlythoigian.classPackage.CustomAdapter;
import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;
import com.gameloft.pc.quanlythoigian.detailscr;
import com.gameloft.pc.quanlythoigian.editscr;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment_saturday extends Fragment {


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

    public TabFragment_saturday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_fragment_saturday, container, false);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

        return view;
    }

    private void init(){
        listMonHoc =  new ArrayList<MonHoc>();
        database = new DatabaseAdapter(TabFragment_saturday.super.getContext());
    }

    private void getWidgets(){
        lvMonHoc = (ListView) view.findViewById(R.id.lvMonHoc);
        btnAdd = (ImageButton) view.findViewById(R.id.btnAdd);

    }

    private void setWidgets(){
        database.open();
        listMonHoc = database.getData(7);
        customAdapter = new CustomAdapter(getActivity(), R.layout.dong_listview, listMonHoc);
        lvMonHoc.setAdapter(customAdapter);
        registerForContextMenu(lvMonHoc);
    }

    private void addWidgetsListener() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabFragment_saturday.super.getContext(),AddActivity.class);
                startActivityForResult(intent,REQUEST_CODE_ADD);
            }
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TabFragment_saturday.super.getContext(),detailscr.class);
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
                    MonHoc monHoc = listMonHoc.get(menuInfo.position);
                    boolean check = database.delete(monHoc,7);
                    if(check){
                        customAdapter.remove(monHoc);
                        customAdapter.notifyDataSetChanged();
                        Toast.makeText(TabFragment_saturday.super.getActivity(),"Đã xóa !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TabFragment_saturday.super.getActivity(),"Sorry! Lỗi cập nhật dữ liệu.",Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.itEdit:
                    Intent iEdit = new Intent(TabFragment_saturday.super.getContext(),editscr.class);
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
                    boolean check = database.AddMonHoc(monHoc,7);
                    if(check){
                        listMonHoc.add(monHoc);
                        customAdapter.notifyDataSetChanged();
                        Toast.makeText(TabFragment_saturday.super.getActivity(),"Đã thêm môn học mới !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TabFragment_saturday.super.getActivity(),"Sorry! Lỗi cập nhật dữ liệu.",Toast.LENGTH_SHORT).show();
                    }
                    //database.close();
            }
        }

        if(requestCode == REQUEST_CODE_EDIT){
            switch (resultCode){
                case RESULT_CODE_EDIT:
                    MonHoc monHoc = (MonHoc) data.getSerializableExtra("monhocEdited");
                    boolean check = database.update(monHoc,7);
                    if(check){
                        listMonHoc = database.getData(7);
                        customAdapter = new CustomAdapter(getActivity(), R.layout.dong_listview, listMonHoc);
                        lvMonHoc.setAdapter(customAdapter);
                        Toast.makeText(TabFragment_saturday.super.getActivity(),"Cập nhật thành công !",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TabFragment_saturday.super.getActivity(),"Sorry! Lỗi cập nhật dữ liệu.",Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }

}
