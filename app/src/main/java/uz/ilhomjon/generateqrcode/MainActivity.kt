package uz.ilhomjon.generateqrcode

import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Display
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder

//documentation: https://www.geeksforgeeks.org/generate-qr-code-in-android-using-kotlin/
class MainActivity : AppCompatActivity() {

    // on below line we are creating a variable
    // for our image view, edit text and a button.
    lateinit var qrIV: ImageView
    lateinit var msgEdt: EditText
    lateinit var generateQRBtn: Button

    // on below line we are creating
    // a variable for bitmap
    lateinit var bitmap: Bitmap

    // on below line we are creating
    // a variable for qr encoder.
    lateinit var qrEncoder: QRGEncoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // on below line we are
        // initializing our all variables.
        qrIV = findViewById(R.id.idIVQrcode)
        msgEdt = findViewById(R.id.idEdt)
        generateQRBtn = findViewById(R.id.idBtnGenerateQR)

        // on below line we are adding on click
        // listener for our generate QR button.
        generateQRBtn.setOnClickListener {
            // on below line we are checking if msg edit text is empty or not.
            if (TextUtils.isEmpty(msgEdt.text.toString())) {

                // on below line we are displaying toast message to display enter some text
                Toast.makeText(applicationContext, "Enter your message", Toast.LENGTH_SHORT).show()
            } else {
                // on below line we are getting service for window manager
                val windowManager: WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager

                // on below line we are initializing a
                // variable for our default display
                val display: Display = windowManager.defaultDisplay

                // on below line we are creating a variable
                // for point which is use to display in qr code
                val point: Point = Point()
                display.getSize(point)

                // on below line we are getting
                // height and width of our point
                val width = point.x
                val height = point.y

                // on below line we are generating
                // dimensions for width and height
                var dimen = if (width < height) width else height
                dimen = dimen * 3 / 4

                // on below line we are initializing our qr encoder
                qrEncoder = QRGEncoder(msgEdt.text.toString(), null, QRGContents.Type.TEXT, dimen)

                // on below line we are running a try
                // and catch block for initialing our bitmap
                try {
                    // on below line we are
                    // initializing our bitmap
                    bitmap = qrEncoder.encodeAsBitmap()

                    // on below line we are setting
                    // this bitmap to our image view
                    qrIV.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    // on below line we
                    // are handling exception
                    e.printStackTrace()
                }
            }
        }
    }
}