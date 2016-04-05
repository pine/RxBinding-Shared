package moe.pine.rxbinding.shared

class GeneratorTask {
    static main(args) {
        def javaRootPaths = ['./RxBinding/rxbinding/src/main/java/']
        def kotlinPath = './RxBinding/rxbinding-kotlin/src/main/kotlin/com/jakewharton/rxbinding'
        def sharedKotlinPath = './rxbinding-shared-kotlin/src/main/kotlin/moe/pine/rxbinding/shared'

        def views = ['View', 'MenuItem', 'ViewGroup']
        def widgets = [
                'AbsListView',
                'Adapter',
                'AdapterView',
                'AutoCompleteTextView',
                'CheckedTextView',
                'CompoundButton',
                'PopupMenu',
                'ProgressBar',
                'RadioGroup',
                'RatingBar',
                'SearchView',
                'SeekBar',
                'TextSwitcher',
                'TextView',
                'Toolbar'
        ]

        def generator = new CodeGenerator(javaRootPaths)
        def enumTypes = []

        views.each {
            enumTypes.addAll(generator.generate(
                    "moe.pine.rxbinding.shared.view",
                    "$kotlinPath/view/Rx${it}.kt",
                    ['com.jakewharton.rxbinding.view.*', 'android.view.*'],
                    "$sharedKotlinPath/view/RxShared${it}.kt"
            ))
        }

        widgets.each {
            enumTypes.addAll(generator.generate(
                    "moe.pine.rxbinding.shared.widget",
                    "$kotlinPath/widget/Rx${it}.kt",
                    ['com.jakewharton.rxbinding.widget.*', 'android.widget.*', 'android.view.*'],
                    "$sharedKotlinPath/widget/RxShared${it}.kt"
            ))
        }

        generator.generateEnumTypes(enumTypes, "$sharedKotlinPath/ObservableType.kt")
    }
}
