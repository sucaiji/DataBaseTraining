package com.example.sufchick.databasetraining.fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sufchick.databasetraining.R;
import com.example.sufchick.databasetraining.activity.MainActivity;
import com.example.sufchick.databasetraining.sql.Sql;


public class TrainingFragment extends Fragment {

    private MainActivity activity;

    private View view;

    private EditText sqlEditText;

    private Button submitButton;

    private WebView mWebView;

    private Sql sql;

    private SQLiteDatabase db;

    private String html = "";


    public TrainingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_training, container, false);

        activity = (MainActivity) getActivity();

        sql = new Sql(activity, "DatabaseTraining.db", null, 1);

        db = sql.getWritableDatabase();

        findVIew();

        setListener();

        return view;
    }

    private void findVIew() {
        sqlEditText = (EditText) view.findViewById(R.id.training_edit);
        submitButton = (Button) view.findViewById(R.id.training_submit);
        mWebView = (WebView) view.findViewById(R.id.training_web_view);
    }

    private void setListener() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = sqlEditText.getText().toString();
                sqlEditText.setText("");
                //Toast.makeText(activity, "发送成功", Toast.LENGTH_SHORT).show();
                try {
                    addContentln("<b>you:</b>" + str +"<br/>");
                    boolean select = str.matches("^select .*");
                    if (select) {
                        Cursor cursor = db.rawQuery(str, null);

                        if (cursor.moveToFirst()) {
                            addContent("<table>");
                            addContent("<tr>");
                            int columnCount = cursor.getColumnCount();
                            String[] columnName = new String[columnCount];
                            //循环获取每列的名字
                            for (int i = 0; i < columnCount; i++) {
                                columnName[i] = cursor.getColumnName(i);
                                addContent("<th>" + columnName[i] + "</th>");
                            }
                            addContent("</tr>");

                            do {
                                //循环获取每一条数据的内容
                                for (int i = 0; i < columnCount; i++) {
                                    addContent("<td>" + cursor.getString(cursor.getColumnIndex(columnName[i])) + "</td>");
                                }
                            } while (cursor.moveToNext());
                            addContentln("</table>");
                        }
                    } else {
                        db.execSQL(str);
                        addContentln("success<br/>");
                    }


                } catch (Exception e) {
                    Toast.makeText(activity, "catch", Toast.LENGTH_SHORT).show();
                    addContentln("<b>error:</b>" + e.getMessage()+"<br/>");

                }finally {
                    mWebView.loadData(getHtml(), "text/html", "utf-8");
                    mWebView.reload();
                }
            }
        });
    }

    private String getHtml() {
        return "<!DOCTYPE HTML> <html><head>" +
                "<style>table {\n" +
                "\tborder-collapse: collapse;\n" +
                "\tborder-style: solid;\n" +
                "\tborder-width: 2px;\n" +
                "}\n" +
                "table td {\n" +
                "\tborder:2px solid #C7D0D7;\n" +
                "\ttext-align:center;\n" +
                "\tcolor:#4C5154;\n" +
                "\tfont-family:Arial;\n" +
                "\tfont-size:14px;\n" +
                "\tpadding:5px\n" +
                "}</style>"+
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></html><body>"
                + html + "</body></html>";
    }

    private void addContentln(String str) {
        html = html + str + "<br/>";
    }

    private void addContent(String str) {
        html = html + str;
    }

}
