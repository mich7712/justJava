//Librerias
package com.example.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

//clase principal
public class MainActivity extends AppCompatActivity {
    //Definición de variable
    int quantity=1;

    @Override
    //Metodo privado para crear
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //metodo privado para el boton de incrementar

    public void increment(View view){
        if (quantity == 10) {//Se define un limite que es de 10 cafes
            Toast.makeText(this, "You cannot have more than 10 coffes ", Toast.LENGTH_SHORT).show();// En caso de que el usuario pida mas cafes se le mostrara un mensaje indicando el limite de ordenes
            return;
        }
        quantity = quantity +1;//Incrementa el valor de la variable quantity (La orden)
        displayQuantity(quantity);//Muestra el valor
    }
    //Metodo para el boton de decremetar en la orden
    public void decrement(View view){
        if (quantity == 1) {//Define el valor minimo a pedir
            Toast.makeText(this, "You cannot have less than 1 coffes", Toast.LENGTH_SHORT).show();//Mensaje a mostrar si el usuario intenta decrementar mas de lo definido
            return;
        }
        quantity = quantity -1;//Decrementa el valor de la variable quantity (La orden)
        displayQuantity(quantity);//Muestra el valor
    }
    /**
     * This method is called when the order button is clicked.
     */
    //Metodo privado para inivasr orden
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);//Obtenemos texto dado por el usuario (nombre del usuario)
        String name = nameField.getText().toString();//Variable donde se guarda el nombre del usurio
        // Figure out if the user wants whipped cream topping (Averigua si el usuario quiere crema batida)
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);// se obtine valores boolean del CheckBoox
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping (Averiguar si el usuario quiere cobertura de chocolate)
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);// se obtine valores boolean del CheckBoox
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price (Calcule el precio)
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        // Display the order summary on the screen (Mostrar el resumen del pedido en la pantalla)
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        displayMessage(message);
    }

    //Metodo para calcular el precio
    private int calculatePrice (boolean addWhippedCream, boolean addChocolate){
        int basePrice = 5;// se define el precio base del cafe

        if (addWhippedCream){//Si el usuario a grego el topping de crema se suma al precio base el valor del topping
            basePrice = basePrice + 1;
        }

        if (addChocolate){//Si el usuario a grego el topping de chocloate se suma al precio base el valor del topping
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;//Se multiplica la cantida de cafes  por el precio base
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name){//Obteniene parametros
        //Emprime un mensaje mas los valores de los parametros obtenidos
        String priceMessage = "Name " + name;
        priceMessage += "\nAdd whipped cream?" + addWhippedCream;
        priceMessage += "\nAdd whipped chocolate?" + addChocolate;
        priceMessage = priceMessage + "\nQuantity: "+ quantity;
        priceMessage = priceMessage + "\nTotal: $ "+ price;
        priceMessage = priceMessage + "\nthank you!";
        return priceMessage;//Emprime todo
    }



    /**
     * Este método muestra el valor de la cantidad dada en la pantalla.
     */

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}

