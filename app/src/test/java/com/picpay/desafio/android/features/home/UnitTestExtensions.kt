package com.picpay.desafio.android.features.home

import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat

infix fun <T> T.assertInstanceOf(clazz: Class<*>) {
    assertThat(this, instanceOf(clazz))
}