package ru.kfpu.itis.studteacher.ui.statistics;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.deepakbaliga.beautifulgraph.Plotter;
import com.intrusoft.scatter.ChartData;
import com.intrusoft.scatter.PieChart;
import com.intrusoft.scatter.SimpleChart;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.utilities.InjectorUtils;

public class StatisticsActivity extends AppCompatActivity {

    private StatisticsViewModel statisticsViewModel;
    private Spinner spinner;
    private PieChart pieChart;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        getSupportActionBar().setTitle("Статистика");

        spinner = findViewById(R.id.spinner_stats);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.stats_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        pieChart.setVisibility(View.VISIBLE);
                        graph.setVisibility(View.GONE);
                        break;
                    case 1:
                        pieChart.setVisibility(View.GONE);
                        graph.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        pieChart = (PieChart) findViewById(R.id.pie_chart);
        List<ChartData> data = new ArrayList<>();
        int first = 10;
        int second = 55;
        int thirth = 25;
        int fourth = 10;
        data.add(new ChartData("Отлично - " + first, first, Color.DKGRAY, Color.parseColor("#FFD600")));
        data.add(new ChartData("Хорошо - " + second, second, Color.WHITE, Color.parseColor("#0091EA")));     //ARGS-> (display text, percentage)
        data.add(new ChartData("Удовл - " + thirth, thirth, Color.WHITE, Color.parseColor("#33691E")));
        data.add(new ChartData("Не удовл - " + fourth, fourth, Color.DKGRAY, Color.parseColor("#F57F17")));
        pieChart.setChartData(data);
        pieChart.setAboutChart("Оценки");


        graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.setTitle("Количество учеников, решивших задание");
        graph.addSeries(series);

        StatisticsViewModelFactory factory = InjectorUtils.provideStatisticsViewModelFactory(this.getApplicationContext());
        statisticsViewModel = ViewModelProviders.of(this, factory).get(StatisticsViewModel.class);
    }
}
