package com.overlord.processor


import com.overlord.annotations.BaseURL
import com.overlord.annotations.GET
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

data class Route(val functionName: String, val route: String)

class Processor: AbstractProcessor() {
    override fun getSupportedAnnotationTypes() = mutableSetOf(BaseURL::class.java.canonicalName)

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: return false

        roundEnv?.getElementsAnnotatedWith(BaseURL::class.java)
            ?.forEach {
                val packageName = processingEnv.elementUtils.getPackageOf(it).toString()
                val modelName = it.simpleName.toString()
                val annotation = it.getAnnotation(BaseURL::class.java)

                val routes = it.enclosedElements.mapNotNull {
                    val getRequest = it.getAnnotation(GET::class.java)
                    if (getRequest == null)
                        null
                    else {
                        val elementName = it.simpleName.toString()
                        val route = getRequest.route
                        Route(elementName, route)
                    }
                }

                val fileName = "${modelName}Impl"
                FileSpec.builder(packageName, fileName)
                    .addType(
                        TypeSpec.classBuilder(fileName)
                            .addModifiers(KModifier.PUBLIC)
                            .primaryConstructor(FunSpec.constructorBuilder()
                                .build()
                            ).addProperty(PropertySpec.builder("baseURL", ClassName("kotlin", "String"))
                                .initializer("%S", annotation.baseURL)
                                .build()
                            ).addFunctions(routes)
                            .build())
                    .build()
                    .writeTo(File(kaptKotlinGeneratedDir))
            }

        return true;
    }

    override fun getSupportedSourceVersion() = SourceVersion.RELEASE_8

    private fun TypeSpec.Builder.addFunctions(routes: List<Route>) = apply {
        routes.forEach {
            addFunction(
                FunSpec.builder(it.functionName)
                    .returns(ClassName("kotlin", "String"))
                    .addStatement("return %S", it.route)
                    .build()
            )
        }
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}
