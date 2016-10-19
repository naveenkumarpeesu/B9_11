package com.example.naveenkumar.button;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button, button1,button3;
    private TextView textView;
    private DatePicker datePicker;

    //public static final String key_name = "com.practice.firstApp.ke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button3 =(Button)findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        button.setOnClickListener(clickListener);
        button1.setOnClickListener(getClickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    DialogFragment timePicker = new TimePicker();
                    timePicker.show(getSupportFragmentManager(), "Select a time");
                    break;
                case R.id.button3:
                    LoadImageFromWebOperations("https://www.google.com/search?hl=en&site=imghp&tbm=isch&source=hp&biw=1366&bih=643&q=android&oq=android&gs_l=img.3..0l10.26.4193.0.4447.20.15.4.1.1.0.193.1294.7j6.13.0....0...1ac.1.64.img..2.16.1125...0i10k1j0i10i24k1.4jU_25AFCn4#imgrc=KF0w-bfweJ3WyM%3A");
                    break;
            }

        }
    };
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, false);
        }

        @Override
        public void onTimeSet(android.widget.TimePicker timePicker, int hour, int minute) {

            showTime(hour, minute);
        }

        private void showTime(int hour, int minute) {

            boolean isPM = (hour >= 12);
            textView.setText(String.format("%02d:%02d %s", (hour == 12 || hour == 0) ? 12 : hour % 12, minute, isPM ? "PM" : "AM"));

        }
    }

    View.OnClickListener getClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            DateDialogFragment dialogFragment = new DateDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "Select a date");

        }
    };

    public class DateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {

            showDate(month + "/" + day + "/" + year);

        }

        private void showDate(String s) {
            textView.setText(s);
        }
    }

   /* public class Task extends AsyncTask<String, String, String> {

        private Context context;
        public ProgressDialog pDialog;
        String image_url;
        URL ImageUrl;
        String myFileUrl1;
        Bitmap bmImg = null;
        InputStream is = null;

        public Task(Context context) {
            this.context = context;
        }

        @Override
        protected void onPostExecute(String s) {
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... urls) {


            try {
                HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);


                String path = ImageUrl.getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath()
                        + "/Wallpapers/");
                dir.mkdirs();
                String fileName = idStr;
                File file = new File(dir, fileName);
                FileOutputStream fos = null;
                fos = new FileOutputStream(file);

                bmImg.compress(Bitmap.CompressFormat.JPEG, 75, fos);

                fos.flush();
                fos.close();

                File imageFile = file;
                MediaScannerConnection.scanFile(context,
                        new String[]{imageFile.getPath()},
                        new String[]{"image/jpeg"}, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onPostExecute(String args) {
        // TODO Auto-generated method stub
        super.onPostExecute(args);

        Toast.makeText(MainActivity.this, "Image still loading...",
                Toast.LENGTH_SHORT).show();


        Toast.makeText(MainActivity.this, "Wallpaper Successfully Saved",
                Toast.LENGTH_SHORT).show();

    }*/


}



    /* private void download(View view) {

        new Thread(new Runnable() {

            private Bitmap loadImageFromNetwork(String url) {
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void run() {

                final Bitmap bitmap = loadImageFromNetwork("http://www.google.com/imgres?imgurl=http%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2F7%2F7a%2FBasketball.png&imgrefurl=http%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3ABasketball.png&h=340&w=340&tbnid=EJmjEDyJzrhAuM%3A&zoom=1&docid=C_hn8nOgsGmuwM&hl=en&ei=Q0o2U93LNcaIygH4mICQBQ&tbm=isch&ved=0CHwQhBwwBg&iact=rc&dur=3875&page=1&start=0&ndsp=14");
                imageView.post(new Runnable() {
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });

            }
        }).start();
    }*/

//    View.OnClickListener clickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            new DownloadingTask().execute("http://code.google.com/android/goodies/wallpaper/android-wallpaper5_1024x768.jpg");
//        }
//    };
//
//    public class DownloadingTask extends AsyncTask<String, Void, Bitmap> {
//
//        @Override
//        protected Bitmap doInBackground(String... url) {
//            Bitmap loadImageFromNetwork = null;
//            try {
//                loadImageFromNetwork = BitmapFactory.decodeStream((InputStream) new URL(url[0]).getContent());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return loadImageFromNetwork;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap s) {
//            super.onPostExecute(s);
//            imageView.setImageBitmap(s);
//        }
//    }


