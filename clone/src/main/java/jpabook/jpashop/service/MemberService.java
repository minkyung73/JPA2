package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검증

        memberRepository.save(member);  // 여기에 저장하면 id가 자동으로 generate 될 것
        return member.getId();
    }

    // memberRepository에서 이름으로 이미 회원이 존재하는지 확인
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 수정
     * */
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);   // id로 특정 회원을 찾음
        member.setName(name);   // 그 id를 가진 회원의 이름을 새로 바꿈
    }

    /**
     * 회원 조회
     * */
    // 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    // 전체 조회
    public List<Member> findAll() { return memberRepository.findAll(); }

}