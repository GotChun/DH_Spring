package com.example.ex01.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.example.ex01.domain.dto.MemoDto;

@Mapper
 public interface MemoMapper {

	//어노테이션을 이용한 Mapping
	
	//이 코드가 있어야 insert 시에 최대값을 나타내준다.
	@SelectKey(statement = "select max(id) from tbl_memo",keyProperty= "id",before=false,resultType=int.class)
	@Insert(value =  "insert into tbl_memo values(null,#{text},#{writer},now())" )
	public int Insert(MemoDto memoDto); 
	
	@SelectKey(statement = "select max(id) from tbl_memo",keyProperty= "id",before=false,resultType=int.class)
	@Insert(value =  "insert into tbl_memo values(#{id},#{text},#{writer},now())" )
	public int InsertTx(MemoDto memoDto); 
	
	@Update(value = "update tbl_memo set text=#{text} , writer=#{writer} where id=#{id}")
	public int update(MemoDto memoDto);
	
	
	@Delete(value = "delete from tbl_memo where id = #{id}")
	public int delete(int id);
	
	
	@Select(value = "select * from tbl_memo where id = #{id}")
	public MemoDto select(@Param("id") int id);   //만약 참조변수명이 컬럼명과 다를경우 param 으로 컬럼명이 이거다 하고 지정해준다
	
	
	@Select(value = "select * from tbl_memo")
	public List<MemoDto> selectAll();
	
	@Results(id="MemoResultMap",value= {
			@Result(property = "id",column = "id"),
			@Result(property = "text",column="text"),
	})
	@Select(value = "select id,text from tbl_memo")
	public List<Map<String,Object>> selectAllByResultMap();
	
	
	
	//Xml 을 이용한 Mapping
	public int insert_xml(MemoDto memoDto);
	
	
	public int delete_xml( int tmp);
	
	public MemoDto select_xml(int id);
	
	public int update_xml(MemoDto memoDto);
	
	public List<MemoDto> SelectAll_xml_1();
	public List<Map<String,Object> > selectAll_xml_2();
	public List<Map<String,Object> > selectAll_xml_3();
	public List<Map<String,Object> > select_if_xml(Map<String,Object> param);
	public List<Map<String,Object> > select_when_xml(Map<String,Object> param);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
