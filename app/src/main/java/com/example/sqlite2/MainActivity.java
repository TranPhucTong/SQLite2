package com.example.sqlite2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TenDDHelper tenDDHelper;
    ListView lv ;
    ArrayList<TenDD> arrayList;
    TenDDAdapter adapter;
    EditText edtTen;
    Button btnThem,btnCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnThem.setBackgroundColor(Color.parseColor("#C94820"));
                btnCC.setBackgroundColor(Color.parseColor("#C66A4E"));
                String bai = edtTen.getText().toString().trim();
                if(TextUtils.isEmpty(bai)){
                    Toast.makeText( MainActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                    return;
                }
                tenDDHelper.QueryData("INSERT INTO PhucTong VALUES(null, '"+bai+"')");
                actionGetData();
            }
        });

        btnCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCC.setBackgroundColor(Color.parseColor("#C94820"));
                btnThem.setBackgroundColor(Color.parseColor("#C66A4E"));
                edtTen.setText("");
            }
        });

        arrayList = new ArrayList<>();
        adapter = new TenDDAdapter(this, R.layout.dong_noi_dung, arrayList);
        lv.setAdapter(adapter);

        tenDDHelper = new TenDDHelper(this, "BaiHoc.sqlite", null, 1);
        tenDDHelper.QueryData("INSERT INTO PhucTong VALUES(null, '1.Đà Lạt')");
//        tenDDHelper.QueryData("INSERT INTO PhucTong VALUES(null, '2.Buôn Mê Thuột')");
//        tenDDHelper.QueryData("INSERT INTO PhucTong VALUES(null, '3.Cần Thơ')");
//        tenDDHelper.QueryData("INSERT INTO PhucTong VALUES(null, '4.Phú Quốc')");
//        tenDDHelper.QueryData("INSERT INTO PhucTong VALUES(null, '5.Lý Sơn')");
//        tenDDHelper.QueryData("INSERT INTO PhucTong VALUES(null, '6.Cần Giờ')");
//        tenDDHelper.QueryData("INSERT INTO PhucTong VALUES(null, '7.Côn Đảo')");
//        tenDDHelper.QueryData("INSERT INTO PhucTong VALUES(null, '8.Vũng Tàu')");
        tenDDHelper.QueryData("CREATE TABLE IF NOT EXISTS PhucTong(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenNoiDung VARCHAR(200))");
//

        actionGetData();

    }
    public void DialogXoa(String ten, int id ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có đồng ý xóa "+ten+"?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tenDDHelper.QueryData("DELETE FROM PhucTong WHERE Id = '"+id+"'");
                actionGetData();

            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    public void DialogUpdate( String ten, int id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);

        EditText edtSua = (EditText) dialog.findViewById(R.id.edtSua);
        Button btnSua = (Button) dialog.findViewById(R.id.btnSua);
        Button btnXoa = (Button) dialog.findViewById(R.id.btnHuy);

        edtSua.setText(ten);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenMoi = edtSua.getText().toString().trim();
                if(TextUtils.isEmpty(tenMoi)){
                    Toast.makeText(MainActivity.this, "Nội dung cần sửa chưa được nhập", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                tenDDHelper.QueryData("UPDATE PhucTong SET TenNoiDung = '"+tenMoi+"'WHERE Id = '"+id+"'");
                dialog.dismiss();
                actionGetData();
            }
        });
        dialog.show();
    }

    private void actionGetData() {
        Cursor dataDiaDiem = tenDDHelper.GetData("SELECT * FROM PhucTong");
        arrayList.clear();
        while (dataDiaDiem.moveToNext() ){
            String ten = dataDiaDiem.getString(1);
            //Toast.makeText(this,ten, Toast.LENGTH_SHORT).show();
            int id= dataDiaDiem.getInt(0);
            arrayList.add(new TenDD(id, ten));
        }
        adapter.notifyDataSetChanged();
    }

    private void anhxa() {
        lv = (ListView) findViewById(R.id.lvNoiDung);
        edtTen = (EditText) findViewById(R.id.edtTen);
        btnThem = (Button ) findViewById(R.id.btnThem);
        btnCC = (Button) findViewById(R.id.btnCC);
    }
}