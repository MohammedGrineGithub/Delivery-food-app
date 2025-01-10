import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.widget.Toast
data class Location(
    var id : Int,
    var address: String,
    var wilayaId: Int,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "address" to address,
        "wilayaId" to wilayaId,
        "latitude" to latitude,
        "longitude" to longitude
    )
    fun checkLocationPermissionAndServices(context: Context) : Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (!isInternetAvailable(context)) {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
            return false
        }
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (isGpsEnabled || isNetworkEnabled) {
                val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { locationObj ->
                        locationObj?.let {
                            latitude = it.latitude
                            longitude = it.longitude
                            val locationText = "${it.latitude}, ${it.longitude}"
                            Toast.makeText(context, "Location: $locationText", Toast.LENGTH_SHORT).show()

                        }

                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(context, "Location not fetched: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
                return true
            } else {
                showEnableLocationServicesDialog(context)
            }
        } else {
            val REQUEST_LOCATION_PERMISSION = 1001
            ActivityCompat.requestPermissions(
                context as android.app.Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
        return false
    }

    fun showEnableLocationServicesDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Enable Location Services")
            .setMessage("Location local_storage_services are required to use this feature. Please enable them in settings.")
            .setPositiveButton("Settings") { dialog: DialogInterface, which: Int ->
                // Open settings to enable location local_storage_services
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                context.startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
            }
            .show()
    }
    @SuppressLint("MissingPermission")
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo != null && networkInfo.isConnected
        }
    }
    companion object {
        fun fromMap(map: Map<String, Any>) = Location(
            id = map["id"] as? Int ?: -1,
            address = map["address"] as? String ?: "",
            wilayaId = map["wilayaId"] as Int,
            latitude = map["latitude"] as Double,
            longitude = map["longitude"] as Double
        )
        fun emptyLocation() : Location {
            return Location(
                id = 0,
                address = "",
                wilayaId = 0,
                latitude = 0.0,
                longitude = 0.0
            )
        }
    }
}
