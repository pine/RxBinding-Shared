package moe.pine.rxbinding.shared

import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.TypeConstructor
import org.jetbrains.kotlin.types.TypeProjection
import org.jetbrains.kotlin.types.typeUtil.isTypeParameter
import org.jetbrains.kotlin.types.typeUtil.supertypes

/**
 * KotlinCompilerExtensions
 * Created by pine on 2016/04/03.
 */

val Pair<KtNamedFunction, SimpleFunctionDescriptor>.body: String?
    get() = this.first.bodyExpression?.text

val Pair<KtNamedFunction, SimpleFunctionDescriptor>.name: String
    get() = this.second.name.identifier

val Pair<KtNamedFunction, SimpleFunctionDescriptor>.returnType: KotlinType?
    get() = this.second.returnType

val Pair<KtNamedFunction, SimpleFunctionDescriptor>.extensionType: KotlinType?
    get() = this.second.extensionReceiverParameter?.type

val Pair<KtNamedFunction, SimpleFunctionDescriptor>.arguments: List<ValueParameterDescriptor>
    get() = this.second.valueParameters

val KotlinType.name: String?
    get() = this.constructor.name

val TypeConstructor.name: String?
    get() = this.declarationDescriptor?.name?.identifier

val TypeProjection.name: String?
    get() = this.type.name

val TypeProjection.supertypes: Collection<KotlinType>
    get() = this.type.constructor.supertypes

fun KotlinType.toGenericsCode() : String? {
    return if(this.isTypeParameter()) {
        "<" + this.name + ": " + this.supertypes().first().name + ">"
    } else {
        this.arguments.toGenericsCode()
    }
}

fun List<ValueParameterDescriptor>.toArgumentsCode(): String {
    return this.map { it.name.identifier + ": " + it.type }.joinToString(", ")
}

fun List<TypeProjection>.toGenericsCode(): String {
    val generics = this.map { it.name + ": " + it.supertypes.first().name }
    return if (generics.isEmpty()) "" else generics.joinToString(", ", prefix = "<", postfix = ">")
}
