<!DOCTYPE html>
<html>
	<head>
		<title>【quartz平台】</title>
		
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" rel="stylesheet">
        <link href="../static/css/home/list.css?v=1" rel="stylesheet">
        
        <script src="../static/js/jquery/jquery-1.7.1.min.js?v=1"></script>
        <script src="../static/js/home/list.js?v=1"></script>
    </head>
    <body>
    	<#assign statusList = {"0":"关闭", "1":"启动"} />
    	<section>
    		<table border="1">
    			<tr><th>ID</th><th>job</th><th>组</th><th>类名</th><th>状态</th><th>操作</th></tr>
    			<#if qzJobList>
    				<#list qzJobList as li>
    					<tr>
    						<td>${(li.id)!''}</td>
    						<td>${(li.name)!''}</td>
    						<td>${(li.groupName)!''}</td>
    						<td>${(li.className)!''}</td>
    						<td>${statusList[(li.status?string)!'']}</td>
    						<td>
    							<a href="javascript:void(0);" onclick="resume(${(li.id)!''})">启动</a>&nbsp;&nbsp;&nbsp;&nbsp;
    							<a href="javascript:void(0);" onclick="pause(${(li.id)!''})">停止</a></td>
						</tr>
    				</#list>
    			</#if>
    		</table>
    	</section>
	</body>
	
</html>