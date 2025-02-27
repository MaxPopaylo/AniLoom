package ua.aniloom.presentation.common.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import ua.aniloom.R
import ua.aniloom.databinding.ViewSearchFieldBinding

class SearchFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ViewSearchFieldBinding =
        ViewSearchFieldBinding.inflate(LayoutInflater.from(context), this, true)

    private var searchListener: ((String) -> Unit)? = null

    private val textField = binding.etSearch

    init {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        with(binding) {
            etSearch.setOnFocusChangeListener { _, hasFocus ->
                TransitionManager.beginDelayedTransition(binding.root, AutoTransition().setDuration(200))
                bSearchCancel.isVisible = hasFocus
            }

            etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    searchListener?.invoke(s.toString())
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            bSearchCancel.setOnClickListener {
                etSearch.text.clear()
                etSearch.clearFocus()
                imm.hideSoftInputFromWindow(etSearch.windowToken, 0)
            }
        }
    }

    fun <T> setItems(
        items: List<T>,
        labelProvider: (T) -> String,
        onItemClick: (T) -> Unit
    ) {
        binding.buttonContainer.removeAllViews()
        items.forEach { item ->
            val buttonStyle = R.style.Widget_AniLoom_Button_Primary
            val button = MaterialButton(
                ContextThemeWrapper(context, buttonStyle),
                null,
                buttonStyle
            ).apply {
                layoutParams = LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    dpToInt(38f)
                ).apply {
                    setMargins(dpToInt(8f), 0, dpToInt(8f), 0)
                    setPaddingRelative(dpToInt(18f), paddingTop, dpToInt(18f), paddingBottom)
                }
                text = labelProvider(item)
                backgroundTintList = null
                setBackgroundResource(R.drawable.background_button_click)
                isAllCaps = false
                setOnClickListener { onItemClick(item) }
            }
            binding.buttonContainer.addView(button)
        }
    }

    fun setOnSearchListener(listener: (String) -> Unit) {
        searchListener = listener
    }

    fun getText(): String = textField.text.toString()

    private fun dpToInt(value: Float): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        resources.displayMetrics
    ).toInt()
}

