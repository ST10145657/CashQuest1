package vcmsa.projects.cashquest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateCategoryActivity : AppCompatActivity() {

    // View declarations
    private lateinit var minGoalInput: EditText
    private lateinit var maxGoalInput: EditText
    private lateinit var createCategoryButton: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        minGoalInput = findViewById(R.id.minGoalInput)
        maxGoalInput = findViewById(R.id.maxGoalInput)
        createCategoryButton = findViewById(R.id.createCategoryButton)
        resultText = findViewById(R.id.resultText)
    }

    private fun setupListeners() {
        createCategoryButton.setOnClickListener {
            handleCreateCategory()
        }
    }

    private fun handleCreateCategory() {
        val minGoalStr = minGoalInput.text.toString()
        val maxGoalStr = maxGoalInput.text.toString()

        if (minGoalStr.isBlank() || maxGoalStr.isBlank()) {
            showToast(R.string.error_empty_fields)
            return
        }

        try {
            val minGoal = minGoalStr.toDouble()
            val maxGoal = maxGoalStr.toDouble()

            if (minGoal > maxGoal) {
                showToast(R.string.error_min_greater_than_max)
            } else {
                val result = getString(R.string.budget_goal_result, minGoal, maxGoal)
                resultText.text = result
            }
        } catch (e: NumberFormatException) {
            showToast(R.string.error_invalid_number)
        }
    }

    private fun showToast(messageResId: Int) {
        Toast.makeText(this, getString(messageResId), Toast.LENGTH_SHORT).show()
    }
}