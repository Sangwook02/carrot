package market.carrot.Domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Embeddable;

public enum Category {
    // ‘디지털기기’, ‘생활가전’, ‘가구/인테리어’, ‘유아동’, ‘생활/가공식품’,
    DIGITALDEVICE, HOUSEHOLDAPPLIANCES, FURNITUREINTERIOR, CHILDREN, LIFESTYLE,
    // ‘유아도서’, ‘스포츠/레저’, ‘여성잡화’, ‘여성의류’, ‘남성패션/잡화’,
    CHILDRENBOOKS, SPORTS, WOMENACCESSORY, WOMENCLOTHES, MENACCESSORY, MENCLOTHES,
    // ‘게임/취미’, ‘뷰티/미용’, ‘반려동물용품’, ‘도서/티켓/음반’, ‘식물’,
    GAME, BEAUTY, PET, BOOKTICKETRECORD, PLANT,
    // ‘기타 중고물품’, ‘중고차’
    ETC, USEDCAR;
}