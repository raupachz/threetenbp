/*
 * Copyright (c) 2010-2011, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package javax.time.calendrical;

import static org.testng.Assert.assertEquals;

import javax.time.LocalDate;
import javax.time.LocalDateTime;

import org.testng.annotations.Test;

/**
 * Test ISO AlignedWeekOfMonth rule.
 *
 * @author Stephen Colebourne
 */
@Test
public class TestISOAlignedWeekOfMonthRule extends AbstractTestDateTimeRule {

    public TestISOAlignedWeekOfMonthRule() {
        super(LocalDate.of(2009, 12, 26), 4);
    }

    @Override
    protected DateTimeRule rule() {
        return ISODateTimeRule.ALIGNED_WEEK_OF_MONTH;
    }

    //-----------------------------------------------------------------------
    // Basics
    //-----------------------------------------------------------------------
    public void test_basics() throws Exception {
        DateTimeRule rule = ISODateTimeRule.ALIGNED_WEEK_OF_MONTH;
        assertEquals(rule.getType(), DateTimeField.class);
        assertEquals(rule.getName(), "AlignedWeekOfMonth");
        assertEquals(rule.getValueRange(), DateTimeRuleRange.of(1, 4, 5));
        assertEquals(rule.getPeriodUnit(), ISOPeriodUnit.WEEKS);
        assertEquals(rule.getPeriodRange(), ISOPeriodUnit.MONTHS);
    }

    //-----------------------------------------------------------------------
    // getMaximumValue(Calendrical)
    //-----------------------------------------------------------------------
    public void test_getMaximumValue_Calendrical_nonLeapFeb() {
        Calendrical cal = LocalDate.of(2007, 2, 1);
        assertEquals(rule().getValueRange(cal), DateTimeRuleRange.of(1, 4));
    }

    public void test_getMaximumValue_Calendrical_leapFeb() {
        Calendrical cal = LocalDate.of(2008, 2, 1);
        assertEquals(rule().getValueRange(cal), DateTimeRuleRange.of(1, 5));
    }

    public void test_getMaximumValue_Calendrical_nonLeapJune() {
        Calendrical cal = LocalDate.of(2007, 6, 1);
        assertEquals(rule().getValueRange(cal), DateTimeRuleRange.of(1, 5));
    }

    public void test_getMaximumValue_Calendrical_leapJune() {
        Calendrical cal = LocalDate.of(2008, 6, 1);
        assertEquals(rule().getValueRange(cal), DateTimeRuleRange.of(1, 5));
    }

    //-----------------------------------------------------------------------
    // getValue(Calendrical)
    //-----------------------------------------------------------------------
    public void test_getValue_Calendrical_date_nonLeapFeb() {
        LocalDate date = LocalDate.of(2007, 2, 1);
        for (int i = 0; i < 28; i++) {
            int week = (i / 7) + 1;
            assertEquals(rule().getValue(date), rule().field(week));
            date = date.plusDays(1);
        }
    }

    public void test_getValue_Calendrical_date_leapFeb() {
        LocalDate date = LocalDate.of(2008, 2, 1);
        for (int i = 0; i < 29; i++) {
            int week = (i / 7) + 1;
            assertEquals(rule().getValue(date), rule().field(week));
            date = date.plusDays(1);
        }
    }

    public void test_getValue_Calendrical_date_nonLeapJune() {
        LocalDate date = LocalDate.of(2007, 6, 1);
        for (int i = 0; i < 30; i++) {
            int week = (i / 7) + 1;
            assertEquals(rule().getValue(date), rule().field(week));
            date = date.plusDays(1);
        }
    }

    public void test_getValue_Calendrical_date_leapJune() {
        LocalDate date = LocalDate.of(2008, 6, 1);
        for (int i = 0; i < 30; i++) {
            int week = (i / 7) + 1;
            assertEquals(rule().getValue(date), rule().field(week));
            date = date.plusDays(1);
        }
    }

    public void test_getValue_Calendrical_dateTime() {
        assertEquals(rule().getValue(LocalDateTime.of(2007, 2, 20, 12, 30)), rule().field(3));
    }

}
