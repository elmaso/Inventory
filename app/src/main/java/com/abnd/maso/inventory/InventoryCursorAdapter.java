package com.abnd.maso.inventory;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abnd.maso.inventory.data.InventoryContract;

import static com.abnd.maso.inventory.data.InventoryContract.*;

/**
 * Created by mariosoberanis on 11/22/16.
 */

public class InventoryCursorAdapter extends CursorAdapter {

    private static final String TAG = InventoryCursorAdapter.class.getSimpleName();


    protected InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.inventory_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView product_name = (TextView) view.findViewById(R.id.inventory_item_name_text);
        TextView product_quantity = (TextView) view.findViewById(R.id.inventory_item_current_quantity_text);
        TextView product_price = (TextView) view.findViewById(R.id.inventory_item_price_text);
        ImageView product_add_btn = (ImageView) view.findViewById(R.id.sale_product);
        ImageView product_thumbnail = (ImageView) view.findViewById(R.id.product_thumbnail);

        int id = cursor.getColumnIndex(InventoryEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COL_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COL_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COL_PRICE);
        int thumbnailColumnIndex = cursor.getColumnIndex(InventoryEntry.COL_PICTURE);


        final String productName = cursor.getString(nameColumnIndex);
        final int quantity = cursor.getInt(quantityColumnIndex);
        final int products_sold = cursor.getColumnIndex(InventoryEntry.COL_ITEMS_SOLD);
        String productPrice = "$" + cursor.getString(priceColumnIndex) + " Price";

        String productQuantity = String.valueOf(quantity) + " Inventory";

        final Uri uri = InventoryEntry.buildInventoryURI(id);


        product_name.setText(productName);
        product_quantity.setText(productQuantity);
        product_price.setText(productPrice);


        product_add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, productName + " quantity= " + quantity);
                ContentResolver resolver = view.getContext().getContentResolver();
                ContentValues values = new ContentValues();
                Toast.makeText(context, "uri send: " + uri, Toast.LENGTH_LONG).show();
                if (quantity > 0) {
                    int qq = quantity;
                    int yy = products_sold;
                    Log.d(TAG, "new quabtity= " + qq);
                    values.put(InventoryEntry.COL_QUANTITY, --qq);
                    values.put(InventoryEntry.COL_ITEMS_SOLD, ++yy);
                    resolver.update(
                            uri,
                            values,
                            null,
                            null
                    );
                    context.getContentResolver().notifyChange(uri, null);
                }
            }
        });

    }
}
