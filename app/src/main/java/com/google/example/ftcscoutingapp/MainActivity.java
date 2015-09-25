package com.google.example.ftcscoutingapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    EditText teamnumber, matchnumber, teamnumber1, teamnumber2, teamnumber3, teamnumber4;
    Boolean loaded = false;
    String[] teams = new String[5];
    String[] teams2 = new String[5];
    String[] teams3 = new String[5];
    String[] secpageteams = new String[4];
    String[] turtlestwo = new String[5];
    String[] turtles = new String[5];
    String[] secondarray = new String[4];
    String[] teamone = new String[3];
    String[] teamtwo = new String[3];
    String[] teamthree = new String[3];
    String[] teamfour = new String[3];
    String[] newstring = new String[5];
    JSONArray teamstwo = new JSONArray();
    JSONArray matchteams = new JSONArray();
    String teamstring, matchnumberq, teamnumber1s, test;
    TextView text;
    ParseObject teamquery, matchinfo, teamstats;
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
        teamstats = new ParseObject("TeamStats");
        enterteam.setOnClickListener(new View.OnClickListener() {

            int a = 0;

            public void onClick(View v) {
                teamnumber = (EditText) findViewById(R.id.TeamEnterEditText);
                teamstring = teamnumber.getText().toString();
                teams[a] = teamstring;
                teamnumber.setText("");
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


                Button confirm = (Button) findViewById(R.id.confirm);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

////////////////////////////////////////////////////////////////////////////////////////////////////
                        teamnumber1 = (EditText) findViewById(R.id.Team1AutoComplete);
                        teamnumber2 = (EditText) findViewById(R.id.Team2AutoComplete);
                        teamnumber3 = (EditText) findViewById(R.id.Team3AutoComplete);
                        teamnumber4 = (EditText) findViewById(R.id.Team4AutoComplete);
                        teamnumber1s = teamnumber1.getText().toString();


                        for (int i = 0; i < 4; i++) {
                            matchinfo.add("Teams", teams[i]);
                        }
                        // matchinfo.put("MatchNumber", "1");

                        matchinfo.saveInBackground();
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Matchinformation");
                        query.whereEqualTo("MatchNumber", matchnumber.getText().toString());
                        query.findInBackground(new FindCallback<ParseObject>() {

                            public void done(List<ParseObject> l, ParseException e) {

                                if (e == null) {
                                   /* Log.i("qqq", "working1");
                                    for (int i = 0; i < l.size(); i++) {
                                        teamnumber1.setText(l.get(i).getString("test"));
                                        Log.i("qqq", "working2");
                                    }
                                    // teamnumber1.setText(l.get(0).getString("test"));

                                    Log.i("qqq", "working3");*/
                                    Log.i("qqq", "this is running1");
                                    Log.i("qqq", String.valueOf(l.size()));
                                    for (int i = 0; i < l.size(); i++) {
                                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                                        Log.i("qqq", "this is running2");
                                        matchteams = (l.get(i).getJSONArray("Teams"));
                                        test = matchteams.toString();

                                        secondarray = test.split(",");
                                        Log.i("qqq", "this is running3");
                                        for (int d = 0; d < 4; d++) {
                                            teams3[d] = secondarray[d].replaceAll("[^\\d.]", "");

                                        }
                                        Log.i("qqq", teams3[0]);
                                        teamnumber1.setText(teams[0]);
                                        teamnumber2.setText(teams[1]);
                                        teamnumber3.setText(teams[2]);
                                        teamnumber4.setText(teams[3]);
                                    }

                                } else {
                                    Log.d("Error", e.getMessage());
                                }

                            }
                        });


                    }
                });


/////////////////////////////////////////////////////////////////////////////////////////////////////
                Button nextpage = (Button) findViewById(R.id.secondnext);
                nextpage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        setContentView(R.layout.autonomous_input_layout);


                        TextView team1 = (TextView) findViewById(R.id.T1TXT);
                        TextView team2 = (TextView) findViewById(R.id.T2TXT);
                        TextView team3 = (TextView) findViewById(R.id.T3TXT);
                        TextView team4 = (TextView) findViewById(R.id.T4TXT);

                        // replace with whatever is queried from match info
                         team1.setText(teams[0]);
                         team2.setText(teams[1]);
                         team3.setText(teams[2]);
                         team4.setText(teams[3]);
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
                                EditText T1RB, T1CL, T1PK,T2RB, T2CL, T2PK,T3RB, T3CL, T3PK, T4RB, T4CL, T4PK;
                                T1RB = (EditText)findViewById(R.id.T1RB);
                                T2RB = (EditText)findViewById(R.id.T2RB);
                                T3RB = (EditText)findViewById(R.id.T3RB);
                                T4RB = (EditText)findViewById(R.id.T4RB);
                                T1CL = (EditText)findViewById(R.id.T1CL);
                                T2CL = (EditText)findViewById(R.id.T2CL);
                                T3CL = (EditText)findViewById(R.id.T3CL);
                                T4CL = (EditText)findViewById(R.id.T4CL);
                                T1PK = (EditText)findViewById(R.id.T1PK);
                                T2PK = (EditText)findViewById(R.id.T2PK);
                                T3PK = (EditText)findViewById(R.id.T3PK);
                                T4PK = (EditText)findViewById(R.id.T4PK);

                                teamone[0] = T1RB.getText().toString();
                                teamone[1] = T1RB.getText().toString();
                                teamone[2] = T1RB.getText().toString();




                               //setContentView(R.layout.activity_main4);


                            }
                        });
                    }
                });
            }
        });
    }
}






