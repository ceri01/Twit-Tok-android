package com.example.twit_tok;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.twit_tok.data.api.TwokApiInstance;
import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.Profile;
import com.example.twit_tok.domain.model.Twok;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.AddTwokRequest;
import com.example.twit_tok.domain.requests.BasicDebugRequest;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.SidRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;

import java.io.IOException;
import java.util.List;
import retrofit2.Response;

public class ApiTest {
    @Test
    public void registerTest() throws IOException {
        Response<SidRequest> response = TwokApiInstance.getTwokApi().getSid().execute();
        SidRequest sidResponse = response.body();
        assert sidResponse != null;
        System.out.println(sidResponse.getSid());
    }

    @Test
    public void setProfileNameTest() throws IOException {
        UpdateProfileNameRequest en = new UpdateProfileNameRequest();
        en.setSid("Rxvl9SVDA3ADaoKIVV3X");
        en.setName("ceri");

        UpdateProfilePictureRequest ep = new UpdateProfilePictureRequest();
        ep.setSid("Rxvl9SVDA3ADaoKIVV3X");
        ep.setPicture("ciaoo");

        SidRequest sid = new SidRequest("Rxvl9SVDA3ADaoKIVV3X");

        Response<Void> call1 = TwokApiInstance.getTwokApi().setProfileName(en).execute();
        System.out.println(call1.raw());
        Response<Void> call2 = TwokApiInstance.getTwokApi().setProfilePicture(ep).execute();
        System.out.println(call1.raw());

        Response<Profile> profileResponse1 = TwokApiInstance.getTwokApi().getProfile(sid).execute();
        Profile profile = profileResponse1.body();

        assert profile != null;
        System.out.println(profile.getName() + " " + profile.getPicture() + " " + profile.getUid() + " "+ profile.getPversion());
        assertNotEquals("unnamed", profile.getName());
    }

    @Test
    public void addTwok() throws IOException {
        AddTwokRequest at = new AddTwokRequest();
        at.setSid("Rxvl9SVDA3ADaoKIVV3X");
        at.setBgcol("010101");
        at.setFontcol("010101");
        at.setFontsize(0);
        at.setFonttype(0);
        at.setHalign(1);
        at.setValign(1);
        at.setLat(111.111);
        at.setLon(111.111);
        at.setText("muissss");

        Response<Void> call = TwokApiInstance.getTwokApi().addTwok(at).execute();
        System.out.println(call.raw());
    }

    @Test
    public void getTwok() throws IOException {
        BasicDebugRequest wr = new BasicDebugRequest();
        wr.setSid("Rxvl9SVDA3ADaoKIVV3X");
        wr.setTid("4");
        BasicDataRequest wor = new BasicDataRequest();
        wor.setSid("Rxvl9SVDA3ADaoKIVV3X");
        wor.setUid("517548");
        SidRequest s = new SidRequest("Rxvl9SVDA3ADaoKIVV3X");

        Response<Twok> call1 = TwokApiInstance.getTwokApi().getTwokWithUid(wr).execute();
        Twok t1 = call1.body();
        System.out.println(call1.raw());
        System.out.println(t1.toString());
        Response<Twok> call2 = TwokApiInstance.getTwokApi().getTwokWithUid(wor).execute();
        Twok t2 = call2.body();
        System.out.println(call2.raw());
        System.out.println(t2.toString());
        Response<Twok> call3 = TwokApiInstance.getTwokApi().getRandomTwok(s).execute();
        Twok t3 = call3.body();
        System.out.println(call3.raw());
        System.out.println(t3.toString());
    }


    @Test
    public void follow() throws IOException {
        BasicDataRequest b = new BasicDataRequest();
        b.setSid("Rxvl9SVDA3ADaoKIVV3X");
        b.setUid("516605");
        Response<Void> call1 = TwokApiInstance.getTwokApi().follow(b).execute();
        System.out.println(call1.raw());
    }

    @Test
    public void unfollow() throws IOException {
        BasicDataRequest b = new BasicDataRequest();
        b.setSid("Rxvl9SVDA3ADaoKIVV3X");
        b.setUid("516605");
        Response<Void> call1 = TwokApiInstance.getTwokApi().unfollow(b).execute();
        System.out.println(call1.raw());
    }

    @Test
    public void getFollowed() throws IOException {
        SidRequest s = new SidRequest("Rxvl9SVDA3ADaoKIVV3X");
        Response<List<User>> call1 = TwokApiInstance.getTwokApi().getFollowed(s).execute();
        List<User> l = call1.body();
        assert l != null;
        System.out.println(l.size());
        for (User u : l) {
            System.out.println(u.getName());
        }
        System.out.println(l);
    }

    @Test
    public void isFollowed() throws IOException {
        BasicDataRequest b = new BasicDataRequest();
        b.setSid("Rxvl9SVDA3ADaoKIVV3X");
        b.setUid("516605");
        Response<IsFollowed> call1 = TwokApiInstance.getTwokApi().isFollowed(b).execute();
        IsFollowed bl = call1.body();
        System.out.println(call1.raw());
        System.out.println(bl.isFollowed());
    }


}
