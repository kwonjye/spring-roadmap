package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     * 할인
     * @param member 회원
     * @param price 가격
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
