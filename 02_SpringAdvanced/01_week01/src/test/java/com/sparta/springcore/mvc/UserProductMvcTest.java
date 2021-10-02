package com.sparta.springcore.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springcore.controller.ProductController;
import com.sparta.springcore.controller.UserController;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.model.User;
import com.sparta.springcore.model.UserRoleEnum;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.security.WebSecurityConfig;
import com.sparta.springcore.service.KakaoUserService;
import com.sparta.springcore.service.ProductService;
import com.sparta.springcore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(
        controllers = {UserController.class, ProductController.class}, //원래는 컨트롤러 별로 나눠서 테스트하는게 좋음
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
class UserProductMvcTest {
    private MockMvc mvc;//스프링에서 제공됨

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    //가짜빈을 사용해서 디펜던시를 끊음. 난 컨트롤러만 테스트하고싶을 뿐이잖아.
    @MockBean
    UserService userService;

    @MockBean
    KakaoUserService kakaoUserService;

    @MockBean
    ProductService productService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }

    private void mockUserSetup() {
// Mock 테스트 유져 생성
        String username = "제이홉";
        String password = "hope!@#";
        String email = "hope@sparta.com";
        UserRoleEnum role = UserRoleEnum.USER;
        User testUser = new User(username, password, email, role);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("로그인 view")
    void test1() throws Exception {
// when - then
        mvc.perform(get("/user/login")) //Get방식으로 이 주소로 보낸다
                .andExpect(status().isOk()) //200응답받으면
                .andExpect(view().name("login"))//컨트롤러가 login이라는 뷰(타임리프로 만든거)를 받는지 테스트
                .andDo(print());
    }

    @Test
    @DisplayName("회원 가입 요청 처리")
    void test2() throws Exception {
// given
        MultiValueMap<String, String> signupRequestForm = new LinkedMultiValueMap<>();
        //이 주소의 컨트롤러가 지금 @RequestParam으로 받도록 되어있어. 즉 Html의 form에서 보내듯이 보내야 함
        signupRequestForm.add("username", "제이홉");
        signupRequestForm.add("password", "hope!@#");
        signupRequestForm.add("email", "hope@sparta.com");
        signupRequestForm.add("admin", "false");

// when - then
        mvc.perform(post("/user/signup")
                        .params(signupRequestForm)
                ) //원래 실제에선 userService를 거치게 되어있지만 여기선 mock으로 하니까 했다 치고, 바로 응답으로 넘어감
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/login"))
                .andDo(print());
    }

    @Test
    @DisplayName("신규 관심상품 등록")
    void test3() throws Exception {
// given
        this.mockUserSetup();//관심상품 등록하려면 로그인해야하니까, 가짜로 로그인.
        String title = "Apple <b>에어팟</b> 2세대 유선충전 모델 (MV7N2KH/A)";
        String imageUrl = "https://shopping-phinf.pstatic.net/main_1862208/18622086330.20200831140839.jpg";
        String linkUrl = "https://search.shopping.naver.com/gate.nhn?id=18622086330";
        int lPrice = 77000;
        ProductRequestDto requestDto = new ProductRequestDto(
                title,
                imageUrl,
                linkUrl,
                lPrice
        );
        //회원 가입 요청 처리 테스트할 때엔 @RequestParam이어서 form 데이터로 바꿨지만, 여기서는 @RequestBody로 받기 때문에 json형태로 바꾼다->objectMapper사용
        String postInfo = objectMapper.writeValueAsString(requestDto);

// when - then
        mvc.perform(post("/api/products")
                        .content(postInfo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
