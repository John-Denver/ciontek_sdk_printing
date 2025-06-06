package com.example.gmail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ctk.sdk.PosApiHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class PrintActivity extends Activity {

    private static final String TAG = "PrintActivity";

    // UI Elements
    private RadioGroup densityRadioGroup;
    private RadioButton rbDensity1, rbDensity2, rbDensity3, rbDensity4, rbDensity5;
    private TextView textViewDensity;
    private Button printButton;
    private EditText amountField;
    private EditText cessPointField;
    private EditText numberPlateField;
    private EditText destinationField;
    private EditText itemField;
    private EditText quantityField;

    // Printer
    private PosApiHelper posApiHelper = PosApiHelper.getInstance();
    private int currentDensity = 2; // Default density

    // Receipt data
    private String currentReceiptNumber = "";
    private String currentBillNumber = "";
    private String currentTimestamp = "";

    // Thread management
    private PrintThread printThread = null;
    private boolean isThreadFinished = true;
    private boolean isWorking = false;

    // SharedPreferences
    private SharedPreferences sharedPreferences;
    private static final String DENSITY_PREF = "PrintDensity";
    private static final String DENSITY_KEY = "density_value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Full screen setup
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_print);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(DENSITY_PREF, Context.MODE_PRIVATE);

        // Initialize views
        initializeViews();

        // Initialize density settings
        initializeDensity();

        // Set up listeners
        setupListeners();
    }

    private void initializeViews() {
        // Density controls
        densityRadioGroup = findViewById(R.id.rg_Gray_type);
        rbDensity1 = findViewById(R.id.RadioButton_high);
        rbDensity2 = findViewById(R.id.RadioButton_middle);
        rbDensity3 = findViewById(R.id.radioButton_low);
        rbDensity4 = findViewById(R.id.radioButton_4);
        rbDensity5 = findViewById(R.id.radioButton_5);
        textViewDensity = findViewById(R.id.textview_Gray);

        // Print button
        printButton = findViewById(R.id.printButton);

        // Input fields
        amountField = findViewById(R.id.amountField);
        cessPointField = findViewById(R.id.cessPointField);
        numberPlateField = findViewById(R.id.numberPlateField);
        destinationField = findViewById(R.id.destinationField);
        itemField = findViewById(R.id.itemField);
        quantityField = findViewById(R.id.quantityField);
    }

    @SuppressLint("SetTextI18n")
    private void initializeDensity() {
        // Load saved density
        currentDensity = sharedPreferences.getInt(DENSITY_KEY, 2);

        try {
            posApiHelper.PrintSetGray(currentDensity);
        } catch (RemoteException e) {
            Log.e(TAG, "Error setting initial density", e);
        }

        // Set UI based on saved density
        String densityText = "Print Density: ";
        switch (currentDensity) {
            case 1:
                rbDensity1.setChecked(true);
                textViewDensity.setText(densityText + "1 (Lightest)");
                break;
            case 2:
                rbDensity2.setChecked(true);
                textViewDensity.setText(densityText + "2 (Light)");
                break;
            case 3:
                rbDensity3.setChecked(true);
                textViewDensity.setText(densityText + "3 (Medium)");
                break;
            case 4:
                rbDensity4.setChecked(true);
                textViewDensity.setText(densityText + "4 (Dark)");
                break;
            case 5:
                rbDensity5.setChecked(true);
                textViewDensity.setText(densityText + "5 (Darkest)");
                break;
        }
    }

    private void setupListeners() {
        // Density selection listener
        densityRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (isWorking) {
                    Log.w(TAG, "Printer is working, density change ignored");
                    return;
                }

                String densityText = "Print Density: ";
                int newDensity = 2; // default

                if (checkedId == R.id.RadioButton_high) {
                    newDensity = 1;
                    textViewDensity.setText(densityText + "1 (Lightest)");
                } else if (checkedId == R.id.RadioButton_middle) {
                    newDensity = 2;
                    textViewDensity.setText(densityText + "2 (Light)");
                } else if (checkedId == R.id.radioButton_low) {
                    newDensity = 3;
                    textViewDensity.setText(densityText + "3 (Medium)");
                } else if (checkedId == R.id.radioButton_4) {
                    newDensity = 4;
                    textViewDensity.setText(densityText + "4 (Dark)");
                } else if (checkedId == R.id.radioButton_5) {
                    newDensity = 5;
                    textViewDensity.setText(densityText + "5 (Darkest)");
                }

                currentDensity = newDensity;
                saveDensityPreference(newDensity);

                try {
                    posApiHelper.PrintSetGray(newDensity);
                } catch (RemoteException e) {
                    Log.e(TAG, "Error setting density", e);
                }
            }
        });

        // Print button listener
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputFields()) {
                    generateReceiptDetails();
                    startPrinting();
                }
            }
        });
    }

    private void saveDensityPreference(int density) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(DENSITY_KEY, density);
        editor.apply();
    }

    private boolean validateInputFields() {
        if (amountField.getText().toString().trim().isEmpty()) {
            amountField.setError("Amount is required");
            return false;
        }
        if (cessPointField.getText().toString().trim().isEmpty()) {
            cessPointField.setError("Cess Point is required");
            return false;
        }
        if (numberPlateField.getText().toString().trim().isEmpty()) {
            numberPlateField.setError("Number Plate is required");
            return false;
        }
        if (destinationField.getText().toString().trim().isEmpty()) {
            destinationField.setError("Destination is required");
            return false;
        }
        if (itemField.getText().toString().trim().isEmpty()) {
            itemField.setError("Item is required");
            return false;
        }
        if (quantityField.getText().toString().trim().isEmpty()) {
            quantityField.setError("Quantity is required");
            return false;
        }
        return true;
    }

    private void generateReceiptDetails() {
        currentReceiptNumber = generateReceiptNumber();
        currentBillNumber = generateBillNumber();
        currentTimestamp = formatDateAndTime();
    }

    private void startPrinting() {
        if (printThread != null && !isThreadFinished) {
            Log.w(TAG, "Print thread is still running");
            return;
        }

        printThread = new PrintThread();
        printThread.start();
    }

    private class PrintThread extends Thread {
        @Override
        public void run() {
            Message msg = new Message();
            synchronized (this) {
                isThreadFinished = false;

                try {
                    // Initialize printer
                    int initResult = posApiHelper.PrintInit();
                    Log.d(TAG, "Printer init result: " + initResult);

                    // Set density
                    posApiHelper.PrintSetGray(currentDensity);

                    // Check printer status
                    int statusResult = posApiHelper.PrintCheckStatus();
                    if (statusResult == -1) {
                        sendMessage("Error: No Paper");
                        return;
                    } else if (statusResult == -2) {
                        sendMessage("Error: Printer Too Hot");
                        return;
                    } else if (statusResult == -3) {
                        sendMessage("Error: Low Battery");
                        return;
                    }

                    // Disable controls during printing
                    msg.what = DISABLE_CONTROLS;
                    handler.sendMessage(msg);

                    sendMessage("Printing...");

                    // Print logo
                    printLogo();

                    // Print receipt content
                    String receiptContent = buildReceiptContent();
                    posApiHelper.PrintStr(receiptContent);

                    // Start printing
                    int printResult = posApiHelper.PrintStart();

                    // Re-enable controls
                    Message enableMsg = new Message();
                    enableMsg.what = ENABLE_CONTROLS;
                    handler.sendMessage(enableMsg);

                    if (printResult != 0) {
                        Log.e(TAG, "Print failed with result: " + printResult);
                        if (printResult == -1) {
                            sendMessage("Error: No Paper");
                        } else if (printResult == -2) {
                            sendMessage("Error: Too Hot");
                        } else if (printResult == -3) {
                            sendMessage("Error: Low Voltage");
                        } else {
                            sendMessage("Print Failed");
                        }
                    } else {
                        sendMessage("Print Completed Successfully");
                        // Clear fields on successful print
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                clearInputFields();
                            }
                        });
                    }

                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException during printing", e);
                    sendMessage("Print Error: " + e.getMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Exception during printing", e);
                    sendMessage("Print Error: " + e.getMessage());
                } finally {
                    isThreadFinished = true;
                    // Ensure controls are re-enabled
                    Message enableMsg = new Message();
                    enableMsg.what = ENABLE_CONTROLS;
                    handler.sendMessage(enableMsg);
                }
            }
        }
    }

    private void printLogo() {
        try {
            // Load and prepare the logo image
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo2);
            if (originalBitmap != null) {
                Log.d(TAG, "Logo bitmap loaded successfully");

                // Resize to printer width
                int targetWidth = 256;
                float scaleFactor = (float) targetWidth / originalBitmap.getWidth();
                int targetHeight = (int) (originalBitmap.getHeight() * scaleFactor);
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, targetWidth, targetHeight, true);

                // Print the logo using the correct method
                int logoResult = posApiHelper.PrintBmp(resizedBitmap);
                Log.d(TAG, "Logo print result: " + logoResult);

                if (logoResult != 0) {
                    Log.e(TAG, "Logo printing failed with result: " + logoResult);
                }

                // Clean up
                resizedBitmap.recycle();
                originalBitmap.recycle(); // Don't forget to recycle the original bitmap too
            } else {
                Log.e(TAG, "Logo bitmap (R.mipmap.test001) could not be loaded. Check if the file exists and is correctly named.");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error printing logo", e);
        }
    }

    private String buildReceiptContent() throws IOException {
        // Get input values
        double amount = Double.parseDouble(amountField.getText().toString());
        int quantity = Integer.parseInt(quantityField.getText().toString());
        double totalAmount = amount * quantity;

        StringBuilder receipt = new StringBuilder();


        // Header
        receipt.append("  County Government\n");
        receipt.append("   of Kajiado\n");
        receipt.append("--------------------------------\n");
        receipt.append("*** V1 ***\n");
        receipt.append("Receipt No  ").append(currentReceiptNumber).append("\n");
        receipt.append("--------------------------------\n");

        // Total amount
        receipt.append("Amount ").append(String.format("%.1f", totalAmount)).append("\n");
        receipt.append("--------------------------------\n");

        // Transaction details
        receipt.append("Cess Point : ").append(cessPointField.getText()).append("\n");
        receipt.append("Number Plate : ").append(numberPlateField.getText()).append("\n");
        receipt.append("Destination : ").append(destinationField.getText()).append("\n");
        receipt.append("Bill No :").append(currentBillNumber.toUpperCase()).append("\n");
        receipt.append("--------------------------------\n");

        // Item details
        receipt.append("Item: ").append(itemField.getText()).append("\n");
        receipt.append("Amount: ").append(String.format("%.2f", amount)).append("\n");
        receipt.append("Quantity: ").append(quantity).append("\n");
        receipt.append("--------------------------------\n");
        receipt.append("--------------------------------\n");

        // Footer
        receipt.append("Issued by: Test User\n");
        receipt.append("--------------------------------\n");
        receipt.append("DSN: TEST001\n");
        receipt.append("--------------------------------\n");
        receipt.append(currentTimestamp).append("\n");
        receipt.append("--------------------------------\n");
        receipt.append(" KajiadoPay\n");

        return receipt.toString();
    }

    private void clearInputFields() {
        amountField.setText("");
        cessPointField.setText("");
        numberPlateField.setText("");
        destinationField.setText("");
        itemField.setText("");
        quantityField.setText("");
    }

    private String generateReceiptNumber() {
        return String.valueOf(System.currentTimeMillis() % 100000000).substring(0, 8);
    }

    private String generateBillNumber() {
        return java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private String formatDateAndTime() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return String.format("%02d/%02d/%d %02d:%02d", day, month, year, hour, minute);
    }

    private void sendMessage(String message) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("MESSAGE", message);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    // Handler constants
    private static final int ENABLE_CONTROLS = 10;
    private static final int DISABLE_CONTROLS = 11;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DISABLE_CONTROLS:
                    isWorking = true;
                    setControlsEnabled(false);
                    break;

                case ENABLE_CONTROLS:
                    isWorking = false;
                    setControlsEnabled(true);
                    break;

                default:
                    Bundle bundle = msg.getData();
                    String message = bundle.getString("MESSAGE");
                    if (message != null) {
                        Toast.makeText(PrintActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void setControlsEnabled(boolean enabled) {
        rbDensity1.setEnabled(enabled);
        rbDensity2.setEnabled(enabled);
        rbDensity3.setEnabled(enabled);
        rbDensity4.setEnabled(enabled);
        rbDensity5.setEnabled(enabled);
        printButton.setEnabled(enabled);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isWorking) {
            return true; // Prevent back press during printing
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}