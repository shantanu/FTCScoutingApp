package com.google.example.ftcscoutingapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    EditText teamnumber, matchnumber;
    Boolean loaded = false;
    String[] teams = new String[5];
    String[] teams2 = new String[5];
    String[] turtlestwo = new String[5];
    String[] turtles = new String[5];
    String[] newstring = new String[5];
    JSONArray teamstwo = new JSONArray();
    String teamstring, matchnumberq;
    TextView text;
    ParseObject teamquery,matchinfo;
    String list;
    List<Object> al = new ArrayList<>();

    String[] stringarray = new String[5];
    private Spinner spinner, spinner2, spinner3, spinner4;
    String[] teamsarray;
    int b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_app_front_page);


        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "SlG9zvrlCyjen53XU3WUaf3HAYoZQpra08iCLQNC", "vyRgs4rAN6Ukj6qPfm2fzKNXlTbV8n3ALVringOF");


        Button enterteam = (Button) findViewById(R.id.EnterTeamButton);
        teamquery = new ParseObject("TestObject3");
        matchinfo = new ParseObject("Matchinformation");

        enterteam.setOnClickListener(new View.OnClickListener() {

            int a = 0;

            public void onClick(View v) {
                teamnumber = (EditText) findViewById(R.id.TeamEnterEditText);

                teamstring = teamnumber.getText().toString();
                teams[a] = teamstring;
                //   teams2[a] = teamstring;
                teamnumber.setText("");
                //  teamquery.put("string", "temstring");
                teamquery.add("team", teams[a]);

                teamquery.saveInBackground();
                a++;
                if (a > 4) {
                    teamnumber.setText("Max");
                }

            }
        });

        Button update = (Button) findViewById(R.id.LoadButton);
        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject3");
                query.getInBackground("LTUxGR6iww", new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {

                            teamstwo = object.getJSONArray("team");

                            list = teamstwo.toString();
                            Log.i("qqq", String.valueOf(list));
                            //replace with loop when not lazy - anthony youre dumb
                            stringarray = list.split(",");
                            /*teams[0] = stringarray[0].replaceAll("[^\\d.]", "");
                            teams[1] = stringarray[1].replaceAll("[^\\d.]", "");
                            teams[2] = stringarray[2].replaceAll("[^\\d.]", "");
                            teams[3] = stringarray[3].replaceAll("[^\\d.]", "");
                            teams[4] = stringarray[4].replaceAll("[^\\d.]", "");*/
                            for (int i = 0; i < 5; i++) {
                                teams[i] = stringarray[i].replaceAll("[^\\d.]", "");
                            }


                            loaded = true;


                        } else {

                            Log.i("qqq", String.valueOf(e));
                        }
                    }
                });


            }
        });

        Button done = (Button) findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                setContentView(R.layout.second_page);




                text = (TextView) findViewById(R.id.text);
                matchnumber = (EditText) findViewById(R.id.matchnumber);
                spinner = (Spinner) findViewById(R.id.spinner);
                spinner2 = (Spinner) findViewById(R.id.spinner2);
                spinner3 = (Spinner) findViewById(R.id.spinner3);
                spinner4 = (Spinner) findViewById(R.id.spinner4);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, teams);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("qqq1", (String) parent.getItemAtPosition(position));
                        turtles[0] = (String) parent.getItemAtPosition(position);
                        Log.i("qqq2", turtles[0]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                spinner2.setAdapter(adapter);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("qqq7", (String) parent.getItemAtPosition(position));
                        turtles[1] = (String) parent.getItemAtPosition(position);
                        Log.i("qqq4", turtles[1]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                spinner3.setAdapter(adapter);
                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("qqq5", (String) parent.getItemAtPosition(position));
                        turtles[2] = (String) parent.getItemAtPosition(position);
                        Log.i("qqq6", turtles[2]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                spinner4.setAdapter(adapter);
                spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("qqq7", (String) parent.getItemAtPosition(position));
                        turtles[3] = (String) parent.getItemAtPosition(position);
                        Log.i("qqq8", turtles[3]);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                Button confirm = (Button) findViewById(R.id.confirm);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //replace with loop when not lazy - anthony stop being dumb
                        teamsarray = new String[4];
                        for (int i = 0; i < teamsarray.length; i++) {
                            teamsarray[i] = turtles[i];
                        }
                        /*teamsarray[0] = turtles[0];
                        teamsarray[1] = turtles[1];
                        teamsarray[2] = turtles[2];
                        teamsarray[3] = turtles[3];*/
                        matchnumberq = matchnumber.getText().toString();
                        matchinfo.put("MatchNumber", matchnumberq);
                        for (int i = 0; i < 4; i++){
                            matchinfo.add("Teams", teamsarray[i]);
                        }
                        /*matchinfo.add("Teams", teamsarray[0]);
                        matchinfo.add("Teams", teamsarray[1]);
                        matchinfo.add("Teams", teamsarray[2]);
                        matchinfo.add("Teams", teamsarray[3]);*/
                        matchinfo.saveInBackground();
                        matchnumber.setText("");


                        setContentView(R.layout.autonomous_input_layout);


                        TextView team1 = (TextView) findViewById(R.id.T1TXT);
                        TextView team2 = (TextView) findViewById(R.id.T2TXT);
                        TextView team3 = (TextView) findViewById(R.id.T3TXT);
                        TextView team4 = (TextView) findViewById(R.id.T4TXT);
                        team1.setText(teamsarray[0]);
                        team2.setText(teamsarray[1]);
                        team3.setText(teamsarray[2]);
                        team4.setText(teamsarray[3]);


                        Button back = (Button) findViewById(R.id.back);
                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setContentView(R.layout.second_page);

                            }
                        });

                        Button next = (Button) findViewById(R.id.next);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO
                                //setContentView(R.layout.activity_main4);




                            }
                        });


                    }
                });
            }

        });
    }




}
