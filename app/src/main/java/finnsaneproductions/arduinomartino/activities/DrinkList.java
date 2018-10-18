package finnsaneproductions.arduinomartino.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import finnsaneproductions.arduinomartino.BluetoothSppProvider;
import finnsaneproductions.arduinomartino.JsonDrinkProvider;
import finnsaneproductions.arduinomartino.MockConnectionProvider;
import finnsaneproductions.arduinomartino.R;
import finnsaneproductions.arduinomartino.UsbSerialProvider;
import finnsaneproductions.arduinomartino.factories.ConnectionFactory;
import finnsaneproductions.arduinomartino.factories.DrinkFactory;
import finnsaneproductions.arduinomartino.interfaces.DrinkProvider;

public class DrinkList extends Activity {

    @BindView(R.id.drink_listview)
    public ListView drinkListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load layout.
        setContentView(R.layout.activity_drink_list);

        // Inject members.
        ButterKnife.bind(this);

        // Load drink JSON data.
        DrinkFactory.INSTANCE.setProvider(JsonDrinkProvider.loadFromResource(getApplicationContext(), R.raw.drinks));
        DrinkProvider drinks = DrinkFactory.INSTANCE.getProvider();

        // Set connection providers.
        ConnectionFactory.INSTANCE.addSource(new UsbSerialProvider(getApplicationContext()));
        ConnectionFactory.INSTANCE.addSource(new BluetoothSppProvider());

        // Mock connection provider is good for testing.
        ConnectionFactory.INSTANCE.addSource(new MockConnectionProvider());

        // Populate list with drink names.
        if (drinks != null) {
            drinkListView.setAdapter(
                    new ArrayAdapter<String>(
                            this,
                            //android.R.layout.simple_list_item_1,
                            R.layout.drink_list_item,
                            new ArrayList<String>(drinks.getDrinkNames())
                    )
            );
        }

        // Set list item click handler.
        drinkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                switchDrinkDetails(((TextView) view).getText());
            }
        });
    }

    private void switchDrinkDetails(CharSequence drinkName) {
        Intent intent = new Intent(this, DrinkDetails.class);
        intent.putExtra(DrinkDetails.DRINK_NAME, drinkName);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drink_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}
