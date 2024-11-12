package com.example.ex01.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ex01.domain.dto.PersonDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/param")
public class ParameterTestController {
	
	@RequestMapping(value="/p01",method=RequestMethod.GET)
	public void p01(@RequestParam(value="name", required=false) String name) {
		log.info("GET /param/p01..." + name);
	}
	
	@GetMapping("/p02")
	public void p02(@RequestParam(value="name", required=true) String name) {
		log.info("GET /param/p02..." + name);
	}
	
	@PostMapping(value="/p03")
	public void p03(@RequestParam(value="name", required=true) String name) {
		log.info("post /param/p03..." + name);
	}

	@PostMapping(value="/p04")
	public void p04(@RequestBody String name) {
		log.info("post /param/p04..." + name);
	}
	
	@RequestMapping(value="/p05",method=RequestMethod.GET)
	public void p05(@RequestParam(value="name",defaultValue="홍길동") String name) {
		log.info("Post /param/05..." + name);
	}
	
	
	@RequestMapping(value="/p06",method=RequestMethod.GET)
	public void p06(@RequestParam(value="name") String name,
					@RequestParam(value="age") int age,
					@RequestParam(value="addr") String addr
						) 
	{
		log.info("Post /param/06..." + name + age + addr);
	}
	
	
	@RequestMapping(value="/p07",method=RequestMethod.GET)
	public void p07( @ModelAttribute PersonDto dto) {
		log.info("Post /param/p07..." + dto);
	}
	
	@RequestMapping(value="/p08/{name}/{age}/{addr}",method=RequestMethod.GET)
	public void p08( 
					@PathVariable("name") String name,		//url 경로의 특정 위치를 변수로 바꾼다
					@PathVariable int age,
					@PathVariable String addr
			) {
		log.info("Post /param/p08..." + name + " " + age + " " + addr);
	}
	
	
	@RequestMapping(value="/p09/{name}/{age}/{addr}",method=RequestMethod.GET)
	public void p09( @ModelAttribute PersonDto dto
			) {
		log.info("Post /param/p09..." + dto);
	}
	
	
	@GetMapping("/page1")
	public void page1(PersonDto dto,Model model) {
		log.info("get/param/page1..."  +dto);
		model.addAttribute("dto",dto);
		model.addAttribute("test","test_value");
		//포워딩 위치 : /WEB-INF/views/param/page1.jsp
		}
	
	@GetMapping("/page2")
	public String page2(PersonDto dto,Model model) {
		log.info("get/param/page1..."  +dto);
		model.addAttribute("dto",dto);
		model.addAttribute("test","test_value2");
		//포워딩 위치 : /WEB-INF/views/param/page1.jsp
		return "param/page1";  //이것도 일종의 forwarding 처리
		}
	
	@GetMapping("/page3/{name}/{age}/{addr}")
	public String page3(PersonDto dto,Model model) {
		log.info("get/param/page1..."  +dto);
		model.addAttribute("dto",dto);
		model.addAttribute("test","test_value2");
		//포워딩 위치 : /WEB-INF/views/param/page1.jsp
		return "param/page1";  //이것도 일종의 forwarding 처리
		}
	
	// ------------------------------ 지금 하고 있는게 다 파라미터 받는 방법이라고 한다.
	
	
	@GetMapping("/page4/{name}/{age}/{addr}")
	public ModelAndView page4(PersonDto dto) {
		log.info("get/param/page4..."  +dto);
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("dto", dto);
		modelAndView.addObject("test", "test_value_4");
		modelAndView.setViewName("param/page1");
		
		//포워딩 위치 : /WEB-INF/views/param/page1.jsp
		return modelAndView;  //이것도 일종의 forwarding 처리
		}
	
	
	@GetMapping("/page5")
	public String page5(HttpServletRequest req,HttpServletResponse resp) {
		log.info("get /param/page5..");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String addr = req.getParameter("addr");
		log.info(name,age,addr);
		PersonDto dto = new PersonDto(name,0,null);
		req.setAttribute("dto", dto);
		
		HttpSession session = req.getSession();
		log.info("session" + session);
		return "param/page1";
	}
	
	
	//포워딩 
	@GetMapping("/forward1")
	public String f1(Model model) {  //model 은 request 를 가지고 있다 ?
		model.addAttribute("f1","f1Value");
		log.info("param/forward");
		
		return "forward:/param/forward2";  //forward 2 로가고
	}
	
	@GetMapping("/forward2")
	public String f2(Model model) {  //model 은 request 를 가지고 있다 ?
		model.addAttribute("f2","f2Value");
		log.info("param/forward2");
		
		return "forward:/param/forward3";  //3으로 가고
	}
	
	@GetMapping("/forward3")
	public String f3(Model model) {  //model 은 request 를 가지고 있다 ?
		model.addAttribute("f3","f3Value");
		log.info("param/forward3");
		
		return "param/forward_result";   //최종적으로 result 까지
	}
	
//-----------------------//-----------------------//-----------------------
	
	//리다이렉팅
	
	
	
	@GetMapping("/redirect1")
	public String r1(Model model,RedirectAttributes redirectAttributes) {	//데이터를 담는 model 객체
		log.info("/param/redirect...");
//		model.addAttribute("r1","r1Value");		//쿼리스트링으로 속성 전달(파라미터)
		redirectAttributes.addAttribute("r1","r1Value");
		
		redirectAttributes.addFlashAttribute("r1_flush","r1_flush_value"); //세션 객체에 속성으로 들어감,세션에 저장
		return "redirect:/param/redirect2";
	}
	
	@GetMapping("/redirect2")
	public String r2(String r1,@ModelAttribute("r1_flush") String r1_flush,RedirectAttributes redirectAttributes) {
		log.info("/param/redirect...");
//		model.addAttribute("r1","r1Value");		//쿼리스트링으로 속성 전달(파라미터)
		redirectAttributes.addAttribute("r1", r1);
		redirectAttributes.addAttribute("r2","r2Value");
		
		redirectAttributes.addFlashAttribute("r1_flush",r1_flush);
		redirectAttributes.addFlashAttribute("r2_flush","r2_flush_value"); //세션 객체에 속성으로 들어감
		return "redirect:/param/redirect_result";
	}
//	
//	@GetMapping("/redirect3")
//	public String r3(Model model,RedirectAttributes redirectAttributes) {
//		log.info("/param/redirect...");
////		model.addAttribute("r1","r1Value");		//쿼리스트링으로 속성 전달(파라미터)
//		redirectAttributes.addAttribute("r3","r1Value");
//		
//		redirectAttributes.addFlashAttribute("r3_flush","r3_flush_value"); //세션 객체에 속성으로 들어감
//		return "redirect:/param/redirect_result";
//	}
//	
	@GetMapping("redirect_result")		//
	public void r_result(String r1,Model model) {
		log.info("/param/redirect..."+r1);
//		model.addAttribute("r1","r1Value");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}












