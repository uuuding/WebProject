<%@ include file="../common/top.jsp"%>
<div id="Welcome">
    <div id="WelcomeContent">
       <!-- 显示登录用户的firstname -->
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a href="categoryForm?categoryId=FISH"><img src="images/fish_icon.gif" data-id="FISH" class="hover-image" /></a><br/>
            Saltwater, Freshwater <br/>
            <a href="categoryForm?categoryId=DOGS"><img src="images/dogs_icon.gif" data-id="DOG" class="hover-image" /></a><br/>
            Various Breeds <br/>
            <a href="categoryForm?categoryId=CATS"><img src="images/cats_icon.gif" data-id="CAT" class="hover-image" /></a><br/>
            Various Breeds, Exotic Varieties <br/>
            <a href="categoryForm?categoryId=REPTILES"><img src="images/reptiles_icon.gif" data-id="REPTILE" class="hover-image" /></a><br/>
            Lizards, Turtles, Snakes <br/>
            <a href="categoryForm?categoryId=BIRDS"><img src="images/birds_icon.gif" data-id="BIRD" class="hover-image" /></a><br/>
            Exotic Varieties
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="Birds" coords="72,2,280,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT"
                      data-id="BIRD" class="hover-image" />
                <area alt="Fish" coords="2,180,72,250"
                      href="categoryForm?categoryId=FISH" shape="RECT"
                      data-id="FISH" class="hover-image" />
                <area alt="Dogs" coords="60,250,130,320"
                      href="categoryForm?categoryId=DOGS" shape="RECT"
                      data-id="DOG" class="hover-image" />
                <area alt="Reptiles" coords="140,270,210,340"
                      href="categoryForm?categoryId=REPTILES" shape="RECT"
                      data-id="REPTILE" class="hover-image" />
                <area alt="Cats" coords="225,240,295,310"
                      href="categoryForm?categoryId=CATS" shape="RECT"
                      data-id="CAT" class="hover-image" />
                <area alt="Birds" coords="280,180,350,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT"
                      data-id="BIRD" class="hover-image" />
            </map>
            <img height="355" src="images/splash.gif" align="middle"
                 usemap="#estoremap" width="350" /></div>
    </div>

    <div id="Separator">&nbsp;</div>

    <div id="tooltip" class="tooltip"></div>
</div>

<%@ include file="../common/bottom.jsp"%>