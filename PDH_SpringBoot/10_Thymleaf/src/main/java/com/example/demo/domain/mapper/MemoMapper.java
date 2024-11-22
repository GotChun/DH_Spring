package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.MemoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;



@Mapper
 public interface MemoMapper {

	//어노테이션을 이용한 Mapping
	
	@SelectKey(statement = "select max(id) from tbl_memo",keyProperty= "id",before=false,resultType=int.class)
	@Insert(value =  "insert into tbl_memo values(null,#{text},#{writer},now())" )
	public int Insert(MemoDto memoDto);

	@SelectKey(statement = "select max(id) from tbl_memo",keyProperty= "id",before=false,resultType=int.class)
	@Insert(value =  "insert into tbl_memo values(#{id},#{text},#{writer},now())" )
	public int Insert_tx(MemoDto memoDto);

	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
