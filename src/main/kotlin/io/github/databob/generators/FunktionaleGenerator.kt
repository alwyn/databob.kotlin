package io.github.databob.generators

import io.github.databob.Databob
import io.github.databob.Generator
import io.github.databob.Generators
import org.funktionale.either.Either
import org.funktionale.either.toRight
import org.funktionale.option.Option.Some
import org.funktionale.option.toOption
import java.lang.reflect.Type

class FunktionaleGenerator : Generator {

    private val generator = CompositeGenerator(
            Generators.ofType { t, d -> Some(d.mk(Class.forName(t[0].typeName))).toOption() } ,
            Generators.ofType { t, d -> Pair(d.mk(Class.forName(t[0].typeName)), d.mk(Class.forName(t[1].typeName))).toRight() as Either<*, *> }
    )

    override fun mk(type: Type, databob: Databob): Any? = generator.mk(type, databob)
}