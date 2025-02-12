package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // test getTestDataArray()
        val testDataArray = getTestDataArray()
        Log.d("Function Output", "Sorted Random List: $testDataArray")

        // test averageLessThanMedian()
        val doubleList = listOf(2.5, 3.0, 1.5, 4.0, 5.0) // Example list
        val isAverageLessThanMedian = averageLessThanMedian(doubleList)
        Log.d("Function Output", "Average < Median: $isAverageLessThanMedian for list $doubleList")

        // test getView()
        val context = this
        val exampleList = listOf(10, 20, 30, 40, 50)
        val view = getView(2, null, exampleList, context) // Get a view for the 3rd element (index 2)
        Log.d("Function Output", "Generated View Text: ${(view as TextView).text}")
    }


}

    /* Converted all the helper functions to Single-Expression Functions using Scope Functions */

    // Return a list of random, sorted integers
    private fun getTestDataArray() = MutableList(10) { Random.nextInt() }.apply { sort() }

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>) =
        listOfNumbers.sorted().let { sortedList ->
            val median = if (sortedList.size % 2 == 0)
                (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
            else
                sortedList[sortedList.size / 2]
            listOfNumbers.average() < median
        }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context) =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply { text = collection[position].toString() }
}
