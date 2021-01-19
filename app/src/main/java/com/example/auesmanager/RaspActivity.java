package com.example.auesmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auesmanager.pojo.Rasp;
import com.example.auesmanager.pojo.TODO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class RaspActivity extends Activity {
    private TextView Dayl, Timel, SubGroup, Namel, Sensei, Rooml;
    private Collection<Rasp> titleList = new ArrayList<>();
    private Collection<TODO> todoList = new ArrayList<>();
    private ArrayList<String> current_titleList = new ArrayList<>();
    private ArrayList<String> menuList1 = new ArrayList<>();
    private ArrayList<String> menuList2 = new ArrayList<>();

    private RecyclerView raspsRV, todoRV;
    private RecView adapter;
    private TodoView TDadapter;
    public Elements content;
    private FloatingActionButton btnFloating;
    private boolean on_click = false;
    private static FloatingActionButton fab1, fab2;
    private static Spinner spinner_menu, spinner_menu2;
    private Document doc_menu,  doc;
    private ArrayAdapter<String> arrayAdapter1, arrayAdapter2;
    private Context context = this;
    private String href_exam = "";

    AsyncTask<String, Integer, Integer> td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rasp_info);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        btnFloating = findViewById(R.id.fab_add);
        spinner_menu = findViewById(R.id.spinner_menu);
        spinner_menu2 = findViewById(R.id.spinner_menu2);
        Animation show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
        Animation hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
        fab1 = findViewById(R.id.fab_1);
        fab2 = findViewById(R.id.fab_2);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        Dayl = findViewById(R.id.dayl);
        Timel = findViewById(R.id.timel);
        SubGroup = findViewById(R.id.subgroup);
        Namel = findViewById(R.id.namel);
        Sensei = findViewById(R.id.sensei);
        Rooml = findViewById(R.id.rooml);

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.add_goal, null);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                alertDialog.setView(promptsView);
                final EditText userInputGoal = promptsView.findViewById(R.id.editTextGoal);
                final EditText userInputTime = promptsView.findViewById(R.id.editTime);

                alertDialog.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NewData(userInputGoal.getText().toString(), userInputTime.getText().toString());
                    }
                }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
        td = new NewThread().execute("https://aues.arhit.kz/rasp/", "ins", "1");

        initTODO();
        btnFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_click = !on_click;
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
                if (on_click) {
                    layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
                    layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
                    fab1.setLayoutParams(layoutParams);
                    layoutParams = (FrameLayout.LayoutParams) fab2.getLayoutParams();
                    layoutParams.rightMargin += (int) (fab2.getWidth() * 0.7);
                    layoutParams.bottomMargin += (int) (fab2.getHeight() * 1.25);
                    fab2.setLayoutParams(layoutParams);
                    fab1.startAnimation(show_fab_1);
                    fab2.startAnimation(show_fab_1);
                    fab1.setClickable(true);
                    fab2.setClickable(true);
                }
                else  {
                    layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
                    layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
                    fab1.setLayoutParams(layoutParams);
                    layoutParams = (FrameLayout.LayoutParams) fab2.getLayoutParams();
                    layoutParams.rightMargin -= (int) (fab2.getWidth() * 0.7);
                    layoutParams.bottomMargin -= (int) (fab2.getHeight() * 1.25);
                    fab2.setLayoutParams(layoutParams);
                    fab1.startAnimation(hide_fab_1);
                    fab2.startAnimation(hide_fab_1);
                    fab1.setClickable(false);
                    fab2.setClickable(false);
                }
            }
        });
        todoRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
                if (dy > 0 && on_click){
                    layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
                    layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
                    fab1.setLayoutParams(layoutParams);
                    layoutParams = (FrameLayout.LayoutParams) fab2.getLayoutParams();
                    layoutParams.rightMargin -= (int) (fab2.getWidth() * 0.7);
                    layoutParams.bottomMargin -= (int) (fab2.getHeight() * 1.25);
                    fab2.setLayoutParams(layoutParams);
                    fab1.startAnimation(hide_fab_1);
                    fab2.startAnimation(hide_fab_1);
                    fab1.setClickable(false);
                    fab2.setClickable(false);
                    on_click = !on_click;
                }
            }
        });
    }

    private void initRV() {
        raspsRV = findViewById(R.id.ListView1);
        raspsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        RecView.OnRaspClick onRaspClickListener = new RecView.OnRaspClick() {
            @Override
            public void onRaspClick(Rasp rasp) {
                Log.d("MyLog", "Пара - " + rasp.getNamel());
            }
        };
        adapter = new RecView(onRaspClickListener);
        adapter.setHasStableIds(true);
        adapter.setItems(titleList);
        adapter.notifyDataSetChanged();
        raspsRV.setAdapter(adapter);
    }
    private void initTODO(){
        Data();
        todoRV = findViewById(R.id.ListView2);
        todoRV.setLayoutManager(new LinearLayoutManager(this));
        TDadapter = new TodoView();
        TDadapter.setHasStableIds(false);
        TDadapter.setItems(todoList);
        todoRV.setAdapter(TDadapter);
    }
    private void NewData(String textDO, String textTime) {
        TDadapter.setItem(new TODO(textDO, textTime, R.drawable.ic_baseline_assignment, "None"));
    }
    private void Data() {
        todoList.add(new TODO("Привет, это первый пункт в твоем списке, ты можешь изменить его, либо удалить", "21:12", R.drawable.ic_baseline_assignment, "None"));
    }
    private void initMenu(Spinner spinner_menus, ArrayAdapter<String> arrayAdapter){
        spinner_menus.setAdapter(arrayAdapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                String href = "";
                if (!item.equals("--Nothing--") && parent.getId() == spinner_menu.getId()) {
                    for (Element el : doc_menu.select("p>a>ins")) {
                        if (item.equals(el.text())) {
                            href = el.parentNode().attr("href");
                            break;
                        }

                    }
                    if (!href.equals("")) {
                        href_exam = "";
                        if (href.equals("getexam.php")) href_exam = "schedule.php?sg=";
                        td = new NewThread().execute("https://aues.arhit.kz/rasp/" + href, "option", "2");
                        Log.d("MyTag", href);
                    }
                }
                else if (!item.equals("--Nothing--") && parent.getId() == spinner_menu2.getId()){
                    for (Element el : doc.select("select>option")) {
                        if (item.equals(el.text())) {
                            href = el.attr("value");
                            break;
                        }
                    }
                    if (!href.equals("") && href_exam.equals("")){
                        td = new NewThread().execute("https://aues.arhit.kz/rasp/scheduleNew.php?sg="  + href + "&schedule=21", "td", "0");
                        Log.d("MyTag", href);
                    }
                    else if(!href.equals("") && !(href_exam.equals(""))) td = new NewThread().execute("https://aues.arhit.kz/rasp/" + href_exam + href, "tbody>tr>th,td", "0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_menus.setOnItemSelectedListener(itemSelectedListener);

    }
    public class NewThread extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... arg) {
            String buffer = "Понедельник";
            current_titleList.clear();
            menuList2.clear();
            titleList.clear();
            int num = Integer.parseInt(arg[2]);
            try {
                if (num == 0) {
                    doc = Jsoup.connect(arg[0]).get();
                    content = doc.select(arg[1]);
                }
                else if (num == 1) {
                    doc_menu = Jsoup.connect(arg[0]).get();
                    content = doc_menu.select(arg[1]);
                }
                else {
                    doc = Jsoup.connect(arg[0]).get();
                    content = doc.select(arg[1]);
                }

                for (Element contents : content) {
                    current_titleList.add(contents.text());
                }
                if (num == 0) {
                    for (int i = 0; i < current_titleList.size(); i += 6) {
                        if (!current_titleList.get(i).equals("")) {
                            titleList.add(new Rasp(current_titleList.get(i), current_titleList.get(i + 1), current_titleList.get(i + 2), current_titleList.get(i + 3), current_titleList.get(i + 4), current_titleList.get(i + 5)));
                            buffer = current_titleList.get(i);
                        } else
                            titleList.add(new Rasp(buffer, current_titleList.get(i + 1), current_titleList.get(i + 2), current_titleList.get(i + 3), current_titleList.get(i + 4), current_titleList.get(i + 5)));
                    }
                }
                current_titleList.add(0, "--Выберите--");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return num;
        }


        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer){
                case 0:
                    menuList2.clear();
                    menuList2.add(0, "--Выберите--");
                    spinner_menu.setSelection(0);
                    spinner_menu2.setVisibility(View.INVISIBLE);

                    initRV();
                    break;
                case 1:
                    menuList1.addAll(current_titleList);
                    arrayAdapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, menuList1);
                    initMenu(spinner_menu, arrayAdapter1);
                    break;
                case 2:
                    menuList2.addAll(current_titleList);
                    arrayAdapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, menuList2);
                    initMenu(spinner_menu2, arrayAdapter2);
                    spinner_menu2.setVisibility(View.VISIBLE);
                    break;
            }

        }
    }
}