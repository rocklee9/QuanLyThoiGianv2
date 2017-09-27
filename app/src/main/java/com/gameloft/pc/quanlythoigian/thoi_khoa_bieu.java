package com.gameloft.pc.quanlythoigian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;


import com.gameloft.pc.quanlythoigian.classPackage.CustomAdapter;
import com.gameloft.pc.quanlythoigian.classPackage.monHoc;


import java.util.ArrayList;

public class thoi_khoa_bieu extends AppCompatActivity {

    Button btnLeft;
    Button btnRight;
    ImageButton btnAdd;
    ListView lvMonHoc;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_khoa_bieu);

        lvMonHoc = (ListView) findViewById(R.id.lvMonHoc);
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);

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
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.dong_listview, arrayList);
        lvMonHoc.setAdapter(customAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thoi_khoa_bieu.this,editscr.class);
                startActivityForResult(intent,2); //cho` ket qua tra ve tu editscr nhe !!!
            }// bên editscr muốn biết là do bấm nút Add nên nhảy qua thì phải kiểm tra requestCode là 2 nhé
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(thoi_khoa_bieu.this,detailscr.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pos",position); //gui sang vi tri cua mon dc chon --> de hien thi detail
                intent1.putExtra("MyPackage",bundle);//chứ ko có vị trí thì biết hiển thị detail của môn nào ?
                startActivity(intent1); // ko cần trả về dữ liệu, chỉ vào xem detail thôi.
            }
        });

        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(thoi_khoa_bieu.this,editscr.class);
                startActivityForResult(intent,3);
                //tương tự, chỗ này chuyển qua editscr vs mã requestCode = 3 (để phân biệt vs nút bấm Add)
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 2){//kq trả về từ việc thêm thông tin
            //lay thong tin tu editscr nhe (lay Monhoc, thoigian, phong de hien thi len listview)
        }
        if(resultCode == 3){//kq trả về từ việc sửa thông tin
            //lấy thông tin từ editscr để hiển thị lên listview
        }
    }
}

//Tuy nhiên mình vẫn ko hiểu làm thế nào để editscr phân biệt được lúc mở nó lên là do nút Add
//hay do bấm vào item trên ListView
//AE suy nghĩ thử
